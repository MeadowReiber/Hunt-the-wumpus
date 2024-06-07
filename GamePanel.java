import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.imageio.ImageIO;

public class GamePanel extends JPanel implements KeyListener {
    private Image backgroundImage;
    private Image caveBackgroundImage;
    private BufferedImage playerFrontImage;
    private BufferedImage playerBackImage;
    private BufferedImage playerLeftImage;
    private BufferedImage playerRightImage;
    private BufferedImage currentPlayerImage;
    private BufferedImage objectImage;
    private BufferedImage platformImage;
    private BufferedImage coinImage;
    private int playerX;
    private int playerY;
    private int initialPlayerY;
    private int offsetX;
    private int offsetY;
    private final int playerWidth = 50;
    private final int playerHeight = 50;
    private final int objectWidth = 50;
    private final int objectHeight = 50;
    private final int playerStep = 10;
    private final int backgroundStep = 20;
    private final int verticalStep = 5; // New step size for vertical movement in cave background
    private final int jumpHeight = 120;
    private List<int[]> objectPositions;
    private List<int[]> platformPositions;
    private List<Integer> coinCounts;
    private boolean isDialogShowing = false;
    private boolean isPlayerNearObject = false;
    private int nearbyObjectIndex = -1;
    private boolean isCaveBackground = false;
    private boolean isJumping = false;
    private int jumpStartY;
    private int jumpSpeed = 7;
    private boolean isFalling = false;
    private int fallSpeed = 2;
    private boolean isMovingLeft = false;
    private boolean isMovingRight = false;
    private Timer jumpTimer;
    private Chatbox chatbox;
    private Cave cave;
    private Timer platformTimer;
    private int movingPlatformIndex;
    private int movingPlatformDirection = 1;
    private int platformBaseY;
    private JButton arrowButton;
    private JButton shootArrowButton;
    private JButton coinsButton;
    private int arrowCount = 3;
    private int coinCount = 0;
    private int enteredRoomNumber = -1; // Variable to store the entered room number
    private boolean canEnterRoomInCave = false; // Variable to track if the user can enter a room in the cave
    private int triviaCorrectAnswers = 0;

    public GamePanel() {
        cave = new Cave();
        cave.readCaveData("cave_data.txt");

        try {
            loadImages();
            scaleImages();

            objectPositions = new ArrayList<>();
            platformPositions = new ArrayList<>();
            coinCounts = new ArrayList<>();

            initializeObjectPositions();
            initializePlatforms();

            movingPlatformIndex = new Random().nextInt(platformPositions.size());
            platformBaseY = platformPositions.get(movingPlatformIndex)[1];

            startPlatformTimer();

        } catch (IOException e) {
            e.printStackTrace();
        }

        setFocusable(true);
        addKeyListener(this);

        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                initializePlayerPosition();
            }
        });

        chatbox = new Chatbox();
        setLayout(null);
        chatbox.setBounds(50, getHeight() - 150, getWidth() - 100, 100);
        chatbox.setVisible(false);
        add(chatbox);

        arrowButton = new JButton("Arrows: " + arrowCount);
        styleButton(arrowButton);
        arrowButton.setVisible(false);
        arrowButton.addActionListener(e -> {
            if (arrowCount > 0) {
                arrowCount--;
                arrowButton.setText("Arrows: " + arrowCount);
            }
            requestFocusInWindow(); // Return focus to the game panel
        });
        add(arrowButton);

        shootArrowButton = new JButton("Purchase Arrows");
        styleButton(shootArrowButton);
        shootArrowButton.setVisible(false);
        shootArrowButton.addActionListener(e -> {
            if (arrowCount > 0 && coinCount >= 12) {
                coinCount -= 12;  // Deduct 12 coins
                coinsButton.setText("Coins: " + coinCount);  // Update the button label
                // Open the Trivia GUI
                SwingUtilities.invokeLater(() -> {
                    TriviaGUI triviaGUI = new TriviaGUI("trivia_questions.txt", this);
                    triviaGUI.setVisible(true);
                });
            } else {
                JOptionPane.showMessageDialog(this, "You need at least 12 coins to shoot an arrow!");
            }
            requestFocusInWindow(); // Return focus to the game panel
        });
        add(shootArrowButton);

        coinsButton = new JButton("Coins: " + coinCount);
        styleButton(coinsButton);
        coinsButton.setVisible(false);
        add(coinsButton);

        // Start the gravity timer
        Timer gravityTimer = new Timer(30, e -> applyGravity());
        gravityTimer.start();
    }

    private void styleButton(JButton button) {
        button.setFont(new Font("Arial", Font.BOLD, 16));
        button.setBackground(new Color(255, 204, 102));
        button.setForeground(Color.WHITE);
        button.setBorder(new LineBorder(new Color(255, 153, 51), 2, true));
        button.setFocusPainted(false);
        button.setContentAreaFilled(false);
        button.setOpaque(true);
    }

    private void loadImages() throws IOException {
        backgroundImage = ImageIO.read(new File("image1.png"));
        caveBackgroundImage = ImageIO.read(new File("cave.png"));
        playerFrontImage = ImageIO.read(new File("playerfrontside.png"));
        playerBackImage = ImageIO.read(new File("playerbackside.png"));
        playerLeftImage = ImageIO.read(new File("playerleftside.png"));
        playerRightImage = ImageIO.read(new File("playerrightside.png"));
        objectImage = ImageIO.read(new File("image2.png"));
        platformImage = ImageIO.read(new File("platform.png"));
        coinImage = ImageIO.read(new File("coin.png"));
    }

    private void scaleImages() {
        playerFrontImage = scaleImage(playerFrontImage, playerWidth, playerHeight);
        playerBackImage = scaleImage(playerBackImage, playerWidth, playerHeight);
        playerLeftImage = scaleImage(playerLeftImage, playerWidth, playerHeight);
        playerRightImage = scaleImage(playerRightImage, playerWidth, playerHeight);
        objectImage = scaleImage(objectImage, objectWidth, objectHeight);
        platformImage = scaleImage(platformImage, 80, 80);
        coinImage = scaleImage(coinImage, 40, 40);
        currentPlayerImage = playerFrontImage;
    }

    private BufferedImage scaleImage(BufferedImage originalImage, int width, int height) {
        Image tmp = originalImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        BufferedImage scaledImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = scaledImage.createGraphics();
        g2d.drawImage(tmp, 0, 0, null);
        g2d.dispose();
        return scaledImage;
    }

    private void initializePlayerPosition() {
        playerX = getWidth() / 2 - playerWidth / 2;
        playerY = getHeight() / 2 - playerHeight / 2;
        initialPlayerY = playerY;

        offsetX = (backgroundImage.getWidth(this) - getWidth()) / 2;
        offsetY = (backgroundImage.getHeight(this) - getHeight()) / 2;

        updateChatboxBounds();

        repaint();
    }

    private void updateChatboxBounds() {
        chatbox.setBounds(50, getHeight() - 150, getWidth() - 100, 100);
        chatbox.revalidate();
        chatbox.repaint();
    }

    private void initializeObjectPositions() {
        int cols = 6;
        int rows = 5;

        int hSpacing = (backgroundImage.getWidth(this) - objectWidth) / (cols - 1);
        int vSpacing = (backgroundImage.getHeight(this) - objectHeight) / (rows - 1);

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                int x = col * hSpacing;
                int y = row * vSpacing;
                objectPositions.add(new int[]{x, y});
            }
        }
    }

    private void initializePlatforms() {
        int playerInitialX = getWidth() / 2 - playerWidth / 2;
        int playerInitialY = getHeight() / 2 - playerHeight / 2;
        platformBaseY = caveBackgroundImage.getHeight(this) * 4 - getHeight() - 150;

        Random random = new Random();

        platformPositions.clear();
        coinCounts.clear();

        platformPositions.add(new int[]{900 + (int)(Math.random() * 400), platformBaseY - ((int)(Math.random() * 200)-400)});
        coinCounts.add(random.nextInt(2) + 1);

        platformPositions.add(new int[]{1000 + (int)(Math.random() * 400), platformBaseY - ((int)(Math.random() * 200)-400)});
        coinCounts.add(random.nextInt(2) + 1);

        platformPositions.add(new int[]{500 + (int)(Math.random() * 400), platformBaseY - ((int)(Math.random() * 200)-400)});
        coinCounts.add(random.nextInt(2) + 1);

        platformPositions.add(new int[]{200 + (int)(Math.random() * 400), platformBaseY - ((int)(Math.random() * 200)-400)});
        coinCounts.add(random.nextInt(2) + 1);

        platformPositions.add(new int[]{300 + (int)(Math.random() * 400), platformBaseY - ((int)(Math.random() * 200)-400)});
        coinCounts.add(random.nextInt(2) + 1);

        platformPositions.add(new int[]{700 + (int)(Math.random() * 400), platformBaseY - ((int)(Math.random() * 200)-400)});
        coinCounts.add(random.nextInt(2) + 1);

        objectPositions.add(new int[]{1600, 1280});
    }

    private void startPlatformTimer() {
        platformTimer = new Timer(500, e -> {
            int[] pos = platformPositions.get(movingPlatformIndex);
            pos[1] += movingPlatformDirection * 150;
            if (pos[1] >= platformBaseY + 2 || pos[1] <= platformBaseY - 2) {
                movingPlatformDirection *= -1;
            }
            repaint();
        });
        platformTimer.start();
    }

    private void applyGravity() {
        if (isCaveBackground && !isJumping && !isFalling && !isOnPlatform() && !allCoinsCollected()) {
            startFalling();
        }
    }

    private boolean isOnPlatform() {
        for (int[] pos : platformPositions) {
            int platformX = pos[0] - offsetX;
            int platformY = pos[1] - offsetY;
            int platformWidth = 80;
            int platformHeight = 80;
            if (playerX + playerWidth > platformX &&
                playerX < platformX + platformWidth &&
                playerY + playerHeight >= platformY &&
                playerY + playerHeight <= platformY + platformHeight) {
                return true;
            }
        }
        return false;
    }

    private void startFalling() {
        isFalling = true;
        Timer fallTimer = new Timer(30, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (isFalling) {
                    playerY += fallSpeed;
                    if (playerY >= initialPlayerY) {
                        playerY = initialPlayerY;
                        isFalling = false;
                        ((Timer)e.getSource()).stop();
                    }
                }
                repaint();
            }
        });
        fallTimer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        updateChatboxBounds();
        drawBackground(g);
        drawPlatforms(g);
        drawObjects(g);
        drawPlayer(g);
        if (isCaveBackground) {
            updateButtonPositions();
            drawCaveObject(g);  // Draw the object in the cave background
        }
    }

    private void drawBackground(Graphics g) {
        Image bgImage = isCaveBackground ? caveBackgroundImage : backgroundImage;
        int bgWidth = isCaveBackground ? caveBackgroundImage.getWidth(this) * 4 : backgroundImage.getWidth(this);
        int bgHeight = isCaveBackground ? caveBackgroundImage.getHeight(this) * 4 : backgroundImage.getHeight(this);
        g.drawImage(bgImage, -offsetX, -offsetY, bgWidth, bgHeight, this);
    }

    private void drawPlatforms(Graphics g) {
        for (int i = 0; i < platformPositions.size(); i++) {
            int[] pos = platformPositions.get(i);
            g.drawImage(platformImage, pos[0] - offsetX, pos[1] - offsetY, this);
            for (int j = 0; j < coinCounts.get(i); j++) {
                g.drawImage(coinImage, pos[0] - offsetX + 40 * j, pos[1] - offsetY - 40, this);
            }
        }
    }

    private void drawObjects(Graphics g) {
        if (objectImage != null) {
            for (int i = 0; i < objectPositions.size(); i++) {
                int[] pos = objectPositions.get(i);
                BufferedImage numberedObjectImage = addRoomNumberToImage(objectImage, i + 1);
                g.drawImage(numberedObjectImage, pos[0] - offsetX, pos[1] - offsetY, this);
            }
        }
    }

    private void drawCaveObject(Graphics g) {
        int x = 1600 - offsetX; // Adjust as per the position in the cave background
        int y = 1280 - offsetY; // Adjust as per the position in the cave background
        if (enteredRoomNumber > 0) {
            BufferedImage numberedObjectImage = addRoomNumberToImage(objectImage, enteredRoomNumber);
            g.drawImage(numberedObjectImage, x, y, this);
        } else {
            g.drawImage(objectImage, x, y, this); // Draw object without number
        }
    }

    private BufferedImage addRoomNumberToImage(BufferedImage image, int roomNumber) {
        BufferedImage numberedImage = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = numberedImage.createGraphics();
        g2d.drawImage(image, 0, 0, null);
        g2d.setFont(new Font("Arial", Font.BOLD, 18));
        g2d.setColor(Color.WHITE);
        FontMetrics fm = g2d.getFontMetrics();
        String roomNumberStr = String.valueOf(roomNumber);
        int textWidth = fm.stringWidth(roomNumberStr);
        int x = (image.getWidth() - textWidth) / 2;
        int y = (image.getHeight() - fm.getHeight()) / 2 + fm.getAscent();
        g2d.drawString(roomNumberStr, x, y);
        g2d.dispose();
        return numberedImage;
    }

    private void drawPlayer(Graphics g) {
        if (currentPlayerImage != null) {
            g.drawImage(currentPlayerImage, playerX, playerY, this);
        }
    }

    private void updateButtonPositions() {
        int buttonX = getWidth() - 170;
        int buttonY = 20;
        arrowButton.setBounds(buttonX, buttonY, 160, 30);
        shootArrowButton.setBounds(buttonX, buttonY + 40, 160, 30); // Increase the width to 160 to fit the text
        coinsButton.setBounds(buttonX, buttonY + 80, 160, 30); // Position the "Coins" button below the other buttons
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (isJumping && !isCaveBackground) {
            return;
        }

        //note: errors happened so I switched -> to ":"
        switch (key) {
            case KeyEvent.VK_UP: handleUpKey();
            case KeyEvent.VK_DOWN: movePlayerDown();
            case KeyEvent.VK_LEFT: movePlayerLeft();
            case KeyEvent.VK_RIGHT: movePlayerRight();
            case KeyEvent.VK_ENTER: handleEnter();
        }

        if (!isCaveBackground) {
            checkForNearbyObjects();
        } else {
            checkForNearbyObjectsInCave();
        }

        checkForCoinCollisions();
        repaint();
    }

    private void handleUpKey() {
        if (isCaveBackground && allCoinsCollected()) {
            movePlayerUp();
        } else if (isCaveBackground) {
            handleJump();
        } else {
            currentPlayerImage = playerBackImage;
            if (playerY > getHeight() / 2 - playerHeight / 2) {
                playerY -= playerStep;
            } else if (offsetY > 0) {
                offsetY = Math.max(offsetY - backgroundStep, 0);
            } else {
                playerY = Math.max(playerY - playerStep, 0);
            }
        }
    }

    private boolean allCoinsCollected() {
        for (int count : coinCounts) {
            if (count > 0) {
                return false;
            }
        }
        return true;
    }

    private void movePlayerUp() {
        if (playerY > 0) {
            playerY -= verticalStep;
            if (offsetY > 0) {
                offsetY -= verticalStep;
            }
            repaint();
        } else if (offsetY > 0) {
            offsetY = Math.max(offsetY - backgroundStep, 0);
            repaint();
        }
    }

    private void handleJump() {
        if (isCaveBackground) {
            if (!isJumping && !isFalling) {
                isJumping = true;
                jumpStartY = playerY;
                jumpTimer = new Timer(20, new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (isJumping) {
                            if (playerY > jumpStartY - jumpHeight) {
                                playerY -= jumpSpeed;
                                if (isMovingLeft) {
                                    playerX -= playerStep;
                                }
                                if (isMovingRight) {
                                    playerX += playerStep;
                                }
                            } else {
                                isJumping = false;
                                isFalling = true;
                            }
                        } else if (isFalling) {
                            if (!checkPlatformCollision()) {
                                playerY += fallSpeed;
                                if (playerY >= initialPlayerY) {
                                    playerY = initialPlayerY;
                                    isFalling = false;
                                    jumpTimer.stop();
                                }
                            } else {
                                isFalling = false;
                                jumpTimer.stop();
                            }
                        }
                        repaint();
                    }
                });
                jumpTimer.start();
            }
        }
    }

    private void movePlayerDown() {
        currentPlayerImage = playerFrontImage;
        if (playerY < getHeight() / 2 - playerHeight / 2) {
            playerY += playerStep;
        } else if (offsetY < (isCaveBackground ? caveBackgroundImage.getHeight(this) * 4 : backgroundImage.getHeight(this)) - getHeight()) {
            offsetY = Math.min(offsetY + backgroundStep, (isCaveBackground ? caveBackgroundImage.getHeight(this) * 4 : backgroundImage.getHeight(this)) - getHeight());
        } else {
            playerY = Math.min(playerY + playerStep, getHeight() - playerHeight);
        }
    }

    private void movePlayerLeft() {
        currentPlayerImage = playerLeftImage;
        isMovingLeft = true;
        if (playerX > getWidth() / 2 - playerWidth / 2) {
            playerX -= playerStep;
        } else if (offsetX > 0) {
            offsetX = Math.max(offsetX - backgroundStep, 0);
        } else {
            playerX = Math.max(playerX - playerStep, 0);
        }
    }

    private void movePlayerRight() {
        currentPlayerImage = playerRightImage;
        isMovingRight = true;
        if (playerX < getWidth() / 2 - playerWidth / 2) {
            playerX += playerStep;
        } else if (offsetX < (isCaveBackground ? caveBackgroundImage.getWidth(this) * 4 : backgroundImage.getWidth(this)) - getWidth()) {
            offsetX = Math.min(offsetX + backgroundStep, (isCaveBackground ? caveBackgroundImage.getWidth(this) * 4 : backgroundImage.getWidth(this)) - getWidth());
        } else {
            playerX = Math.min(playerX + playerStep, getWidth() - playerWidth);
        }
    }

    private void handleEnter() {
        if (isPlayerNearObject && !isCaveBackground) {
            isCaveBackground = true;
            objectPositions.clear();
            playerX = getWidth() / 2 - playerWidth / 2;
            playerY = getHeight() / 2 - playerHeight / 2;
            initialPlayerY = playerY;
            offsetX = 0;
            offsetY = caveBackgroundImage.getHeight(this) * 4 - getHeight();
            initializePlatforms();
            hideChatbox();
            arrowButton.setVisible(true);  // Show the button when transitioning to the cave background
            shootArrowButton.setVisible(true);  // Show the "Shoot an Arrow" button when transitioning to the cave background
            coinsButton.setVisible(true);  // Show the "Coins" button when transitioning to the cave background
            updateButtonPositions();
            repaint();
        } else if (isDialogShowing) {
            // Show input dialog for user to enter room number
            String input = JOptionPane.showInputDialog(this, "Enter room number:");
            if (input != null && !input.isEmpty()) {
                try {
                    int roomNumber = Integer.parseInt(input);
                    enteredRoomNumber = roomNumber;  // Store the entered room number
                    repaint();  // Repaint to show the room number on the image2.png in the cave background
                    // Get connected rooms from the cave
                    ArrayList<Integer> connectedRooms = cave.getAdjacentRooms(roomNumber);
                    StringBuilder message = new StringBuilder("That room number is connected to rooms ");
                    for (int i = 0; i < connectedRooms.size(); i++) {
                        message.append(connectedRooms.get(i));
                        if (i < connectedRooms.size() - 1) {
                            message.append(", ");
                        }
                    }
                    message.append(".");
                    chatbox.showMessage(message.toString());
                    canEnterRoomInCave = true; // Allow entering the room in the cave
                    isDialogShowing = false;  // Set to false after processing input
                } catch (NumberFormatException e) {
                    // Handle invalid input
                    JOptionPane.showMessageDialog(this, "Please enter a valid number.");
                }
            }
        } else if (canEnterRoomInCave) {
            // User can enter the room in the cave
            showBlackScreenAndReturnToCave();
            canEnterRoomInCave = false; // Reset the flag
        }
    }

    private void showBlackScreenAndReturnToCave() {
        // Show a black screen for 1 second and then return to the cave background
        JPanel blackScreen = new JPanel();
        blackScreen.setBackground(Color.BLACK);
        blackScreen.setBounds(0, 0, getWidth(), getHeight());
        add(blackScreen);
        revalidate();
        repaint();
        
        Timer timer = new Timer(1000, e -> {
            remove(blackScreen);
            revalidate();
            repaint();
            // Zoom into the bottom left corner of the cave background
            zoomIntoBottomLeftCorner();
        });
        timer.setRepeats(false);
        timer.start();
    }

    private void zoomIntoBottomLeftCorner() {
        isCaveBackground = true;
        objectPositions.clear();
        initializePlatforms(); // Reinitialize platforms and coins
        playerX = getWidth() / 2 - playerWidth / 2;
        playerY = getHeight() / 2 - playerHeight / 2;
        initialPlayerY = playerY;
        offsetX = 0; // Reset offsetX to show bottom left corner
        offsetY = caveBackgroundImage.getHeight(this) * 4 - getHeight(); // Set offsetY to bottom left corner
        hideChatbox();
        arrowButton.setVisible(true);
        shootArrowButton.setVisible(true);
        coinsButton.setVisible(true);
        updateButtonPositions();
        repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_LEFT) {
            isMovingLeft = false;
        }
        if (key == KeyEvent.VK_RIGHT) {
            isMovingRight = false;
        }
        currentPlayerImage = playerFrontImage;
        repaint();
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // Not used
    }

    private void checkForNearbyObjects() {
        boolean foundNearbyObject = false;
        for (int i = 0; i < objectPositions.size(); i++) {
            int[] pos = objectPositions.get(i);
            int objectX = pos[0] - offsetX;
            int objectY = pos[1] - offsetY;
            if (Math.abs(playerX - objectX) < playerWidth && Math.abs(playerY - objectY) < playerHeight) {
                foundNearbyObject = true;
                if (nearbyObjectIndex != i) {
                    nearbyObjectIndex = i;
                    showTypewriterDialog(i + 1);
                }
                break;
            }
        }
        isPlayerNearObject = foundNearbyObject;
        if (!foundNearbyObject) {
            nearbyObjectIndex = -1;
            hideChatbox();
        }
    }

    private void checkForNearbyObjectsInCave() {
        boolean foundNearbyObject = false;
        for (int i = 0; i < objectPositions.size(); i++) {
            int[] pos = objectPositions.get(i);
            int objectX = pos[0] - offsetX;
            int objectY = pos[1] - offsetY;
            if (Math.abs(playerX - objectX) < playerWidth && Math.abs(playerY - objectY) < playerHeight) {
                foundNearbyObject = true;
                if (nearbyObjectIndex != i) {
                    nearbyObjectIndex = i;
                    showCaveDialog();
                }
                break;
            }
        }
        isPlayerNearObject = foundNearbyObject;
        if (!foundNearbyObject) {
            nearbyObjectIndex = -1;
            hideChatbox();
        }
    }

    private void checkForCoinCollisions() {
        for (int i = 0; i < platformPositions.size(); i++) {
            int[] pos = platformPositions.get(i);
            for (int j = 0; j < coinCounts.get(i); j++) {
                int coinX = pos[0] + 40 * j - offsetX;
                int coinY = pos[1] - 40 - offsetY;
                if (playerX + playerWidth > coinX && playerX < coinX + 40 &&
                    playerY + playerHeight > coinY && playerY < coinY + 40) {
                    coinCounts.set(i, coinCounts.get(i) - 1);
                    coinCount++;  // Increase the coin count when a coin is collected
                    coinsButton.setText("Coins: " + coinCount);  // Update the button label
                }
            }
        }
    }

    private void showTypewriterDialog(int roomNumber) {
        ArrayList<Integer> adjacentRooms = cave.getAdjacentRooms(roomNumber);
        StringBuilder message = new StringBuilder("This is room number " + roomNumber + ", and it is connected to room numbers ");
        for (int i = 0; i < adjacentRooms.size(); i++) {
            message.append(adjacentRooms.get(i));
            if (i < adjacentRooms.size() - 1) {
                message.append(", ");
            }
        }
        message.append(".");
        chatbox.setVisible(true);
        chatbox.showMessage(message.toString());
    }

    private void showCaveDialog() {
        String message = "Choose which room number you want to go to: ____";
        chatbox.setVisible(true);
        chatbox.showMessage(message);
        isDialogShowing = true;  // Set to true when showing the dialog
    }

    private void hideChatbox() {
        chatbox.setVisible(false);
        isDialogShowing = false;
    }

    private boolean checkPlatformCollision() {
        boolean onPlatform = false;
        for (int[] pos : platformPositions) {
            int platformX = pos[0] - offsetX;
            int platformY = pos[1] - offsetY;
            int platformWidth = 80;
            int platformHeight = 80;
            if (playerX + playerWidth > platformX &&
                playerX < platformX + platformWidth &&
                playerY + playerHeight >= platformY &&
                playerY + playerHeight <= platformY + platformHeight) {
                if (playerX + playerWidth > platformX && playerX < platformX + platformWidth / 2) {
                    playerY = platformY - playerHeight;
                    onPlatform = true;
                }
                break;
            }
        }
        if (!onPlatform && !isJumping) {
            startFalling();
        }
        return onPlatform;
    }

    private void checkPlatformRange() {
        boolean inPlatformRange = false;
        for (int[] pos : platformPositions) {
            int platformX = pos[0] - offsetX;
            int platformY = pos[1] - offsetY;
            int platformWidth = 80;
            int platformHeight = 80;
            if (playerX + playerWidth > platformX && playerX < platformX + platformWidth) {
                inPlatformRange = true;
                break;
            }
        }
        if (!inPlatformRange && !isJumping && !isFalling) {
            startFalling();
        }
    }

    public void returnFromTrivia(int correctAnswers) {
        triviaCorrectAnswers = correctAnswers;
        continueGameFromTrivia();
    }

    private void continueGameFromTrivia() {
        if (triviaCorrectAnswers >= 2) {
            arrowCount += 2;  // Increase arrow count by 2
        }
        arrowButton.setText("Arrows: " + arrowCount);  // Update the button text
        arrowButton.setVisible(true);
        shootArrowButton.setVisible(true);
        coinsButton.setVisible(true);
        updateButtonPositions();
        requestFocusInWindow(); // Return focus to the game panel
        repaint();
    }
}









    
