const dialogue = [
    { character: 'Narrator', text: "In the dead of night, beneath a moon veiled by misty clouds, a lone figure trudged along a desolate forest path." },
    { character: 'Narrator', text: "Unknown to her, she was not alone." },
    { character: 'Human', text: "Who's there? Show yourself!" },
    { character: 'Creature', text: "You venture into realms unknown, mortal. What brings you to these woods?" },
    { character: 'Human', text: "I seek passage to the village beyond. I mean no harm." },
    { character: 'Creature', text: "Harm is but a shadow in these woods, mortal." },
    { character: 'Human', text: "What do you mean? Who are you?" }, 
    { character: 'Creature', text: "Names are as fleeting as the mist. I am known as the Wumpus, guardian of secrets untold." },
    { character: 'Human', text: "Please, I just want to go home. I won't disturb your domain any longer." },
    { character: 'Creature', text: "Very well, mortal. Leave these woods and trouble it no more. Remember to never forget your place in this world." },
    { character: 'Human2', text: "Hello there user! My name is Mika and you'll be helping me kill the wumpus. Click the next button and choose a cave to enter." }
  ];
  
  let currentLine = 0;
  
  const humanImg = document.getElementById('human');
  const creatureImg = document.getElementById('creature');
  const human2Img = document.getElementById('human2');
  const dialogueBox = document.getElementById('dialogue-box');
  const dialogueText = document.getElementById('dialogue-text');
  const nextButton = document.getElementById('next-button');
  const welcomeScreen = document.getElementById('welcome-screen');
  
  function showCharacter(character) {
    humanImg.classList.remove('visible', 'enter', 'exit', 'hidden');
    creatureImg.classList.remove('visible', 'enter', 'exit', 'hidden');
    human2Img.classList.remove('visible', 'enter', 'exit', 'hidden');
    if (character === 'Human') {
        humanImg.classList.add('enter');
        creatureImg.classList.add('hidden');
    } else if (character === 'Creature') {
        creatureImg.classList.add('enter');
        humanImg.classList.add('hidden');
    }
     else if (character == 'Human2') {
        human2Img.classList.add('enter');
        creatureImg.classList.add('hidden');
     }
  }
  
  function typewriter(text, onComplete) {
    let index = 0;
    dialogueText.textContent = ''; 
    const speed = 50;
  
    function type() {
        if (index < text.length) {
            dialogueText.textContent += text.charAt(index);
            index++;
            setTimeout(type, speed);
        } else if (onComplete) {
            onComplete();
        }
    }
    type();
  }
  
  function updateScene() {
    const line = dialogue[currentLine];
  
    if (line.character === 'Narrator') {
        humanImg.classList.add('hidden');
        creatureImg.classList.add('hidden');
        human2Img.classList.add('hidden');
        dialogueBox.classList.add('narrator');
    } else {
        dialogueBox.classList.remove('narrator');
        showCharacter(line.character);
    }
  
    typewriter(line.text, () => {
        if (currentLine < dialogue.length - 1) {
            nextButton.disabled = false; 
        } else {
            nextButton.disabled = true;
        }
    });
  
    nextButton.disabled = true; 
  
    if (currentLine >= dialogue.length - 2) {
        welcomeScreen.classList.remove('hidden');
        welcomeScreen.classList.add('visible-enter');
    }
  }
  
  nextButton.addEventListener('click', () => {
    currentLine++;
    updateScene();
  });
  
  updateScene();