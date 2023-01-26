package wordsearch;


import observer.Subject;
import observer.SoundObserver;
import adapter.*;
import command.SuccessMessage;
import command.MessageCommand;
import command.MessageInvoker;
import command.ShowMessageCommand;
import decorator.MyLabel;
import singleton.GameScore;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.*;
import javax.swing.border.EmptyBorder;


public class BoardDisplay implements ActionListener {
        //singleton
	JLabel labelScore = new JLabel(); //label for score
        

	private JFrame frame; //the frame of the window
	private ArrayList<LetterButton> btnArr = new ArrayList<LetterButton>(); //the array of buttons in the wordsearch
	private JLabel[] labels; //array of labels
	private int length; //length of the wordsearch
	private ArrayList<LetterButton> selectedBtns = new ArrayList<LetterButton>();	//list of buttons that are selected by the user
	private boolean letterVerticalOrientation;	
	private ArrayList<String> wordsToFind = new ArrayList<String>(); //list of words that are left to find
	private ArrayList<String> wordList = new ArrayList<String>();	//list of all the words in the wordsearch
	private String word = "";	//the word the user formed so far by clicking the buttons
	private int numOfWords;

	//observer
	private Subject subject;
	private SoundObserver soundObserver;
        
	//adapter
	MusicPlayerInterface musicPlayer = new MusicPlayerAdapter("src\\resources\\music.wav");
        

	
	/**
	 * Constructor of the class
	 * @param length = the length of the wordsearch
	 * @param numOfWords - the number of words to be found in the wordsearch
	 */
	public BoardDisplay(int length, int numOfWords){
		this.length = length;
		this.numOfWords = numOfWords;
		frame = new JFrame("Wordsearch");
		//Asezare frame pe mijlocuk ecranului
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setLocation(dim.width/2-frame.getSize().width/2-200, dim.height-2000/2-frame.getSize().height/2);
		
		frame.setResizable(false);
		labels = new JLabel[numOfWords];
                
                  
	}
	
	/**
	 * Create a wordsearch and create the grid of buttons
	 * Create a label for each word in the wordsearch 
	 */
	public void buildGrid(){
		
		JOptionPane.showMessageDialog(frame, "Bine ai venit la jocul WordSearch\n Gaseste toate cuvintele " +
				"\n Mult succes!!");
		//adapter
		musicPlayer.play();      
                musicPlayer.repeat();
		GameScore.getInstance(0);
		GameScore.resetScore();
		//generate the wordsearch
		WordsearchGenerator ws = new WordsearchGenerator(numOfWords, length);
		ws.setUp();
		String board [][] = ws.getBoard();
		wordList = ws.getListOfWords();
		wordsToFind =  (ArrayList<String>) ws.getListOfWords().clone();
		
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//Create the main content which displays the buttons of the wordsearch
		JPanel content = new JPanel();
		content.setLayout(new GridLayout(length, length));
		
		//create an array of random letters that can be generated for the wordsearch
		String[] randLetters = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M",  "N", "O", "P", "Q",
				"R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
		Random r = new Random();
		for(int i = 0; i < length; i++){
			for(int j = 0; j < length; j++){
				int n = Math.abs(r.nextInt())%26;
				LetterButton btn;
				if(board[i][j] == null)
					btn = new LetterButton(randLetters[n], i, j );
				else
					btn = new LetterButton(board[i][j], i, j );
				content.add(btn);
				btn.addActionListener(this);
				btnArr.add(btn);
			}
		}

		//Create the bottom panel which displays the label of each word
		JPanel bottomPanel = new JPanel();
                bottomPanel.setBackground(Color.BLACK);
                bottomPanel.setForeground(Color.GRAY);
		bottomPanel.setLayout(new GridLayout(2,5, 30, 10));
		for(int i = 0; i < numOfWords; i++){
			labels[i] = new JLabel(wordList.get(i));
			labels[i].setForeground(Color.yellow);
                        bottomPanel.add(labels[i]);
		}

		JPanel panelScor = new JPanel();
		panelScor.setLayout(new BorderLayout());
                panelScor.setBackground(Color.BLACK);
               

		labelScore.setText("Score: " + String.valueOf(GameScore.getScore()));
                labelScore.setForeground(Color.yellow);
		panelScor.add(labelScore);
                

		bottomPanel.setBorder(new EmptyBorder(10, 20, 20, 20));
		content.setBorder(new EmptyBorder(10, 10, 10, 10));
		Container f = frame.getContentPane();
		f.setLayout(new BoxLayout(f, BoxLayout.Y_AXIS ));

		f.add(content);
		f.add(bottomPanel);
		f.add(panelScor,BorderLayout.WEST);
		frame.pack();
               

	}

	/**
	 * Checks if the player have found a word in the wordsearch
	 * If the player find all the words, the player will have the option to Play Again or Quit
	 */
	
         public static Color getRandomColor() {
        Random rand = new Random();
        int r = rand.nextInt(256);
        int g = rand.nextInt(256);
        int b = rand.nextInt(256);
        return new Color(r, g, b);
    }
         
	 public void checkMatch(){
        	subject = new Subject();
            soundObserver = new SoundObserver();
            subject.registerObserver(soundObserver);
    

        if(wordsToFind.contains(word)){
            int index = wordList.indexOf(word);
            wordsToFind.remove(word);
            

             
            labels[index].setForeground(Color.GRAY);
            GameScore.incrementScore();
            labelScore.setText("Score: " + String.valueOf(GameScore.getScore()));
            subject.notifyObservers();
            for(LetterButton b:selectedBtns){
                b.setSelected(false);
                b.setFoundWord(true);
            }
            
            selectedBtns.clear();
            word = "";
            //Decorator
                MyLabel myLabel = new MyLabel(labelScore);
                myLabel.setColor(getRandomColor());
                       
  
                
            
            
        }
        
        //Check if all the words were found
        if(wordsToFind.size() == 0){
            
            int level=1;
            MessageCommand mes = new SuccessMessage();
            ShowMessageCommand showMessageCommand = new ShowMessageCommand(mes, this, level);
            MessageInvoker invoker = new MessageInvoker(showMessageCommand);
            invoker.execute();
        }
    }


	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource()instanceof LetterButton){
			LetterButton btn = (LetterButton)e.getSource();
			
			if(btn.getSelected()){
				if(btn ==selectedBtns.get(selectedBtns.size()-1)){
					selectedBtns.remove(btn);
					word = word.substring(0, word.length()-1);
				}else if(btn == selectedBtns.get(0)){
					selectedBtns.remove(btn);
					if(word.length() == 1)
						word = "";
					else
						word = word.substring(1);
				}else
					return;
			}else{	
				if(selectedBtns.size()==0){
					selectedBtns.add(btn);
					word = btn.getLetter();
				}else{
					//get orientation of button is only one button was already selected
					if(selectedBtns.size()==1){
						//if the button is in the same row as the other selected button
						if(selectedBtns.get(0).getXPos() == btn.getXPos())
							letterVerticalOrientation = false;
						//if the button is in the same column as the other selected button
						else if(selectedBtns.get(0).getYPos() == btn.getYPos())
							letterVerticalOrientation = true;
						else{
							clearSelectedBtns();
							selectedBtns.add(btn);
							word = btn.getLetter();
							btn.toggle();
							return;
						}
					}
					
					if(letterVerticalOrientation){
						//if the button is beside the reference, on the top
						if(btn.getXPos() == selectedBtns.get(0).getXPos()-1 && btn.getYPos() == selectedBtns.get(0).getYPos()){
							selectedBtns.add(0, btn);
							word = btn.getLetter() + word;
						}//if the button is beside the reference, on the bottom
						else if(btn.getXPos() == selectedBtns.get(selectedBtns.size()-1).getXPos()+1  && btn.getYPos() == selectedBtns.get(selectedBtns.size()-1).getYPos()){
							selectedBtns.add(btn);
							word = word + btn.getLetter();
						}else{	
							clearSelectedBtns();
							selectedBtns.add(btn);
							word = btn.getLetter();
						}
					}else{
						//if the button is beside the reference, on the left
						if(btn.getYPos() == selectedBtns.get(0).getYPos()-1  && btn.getXPos() == selectedBtns.get(0).getXPos()){
							selectedBtns.add(0, btn);
							word = btn.getLetter() + word;
						}//if the button is beside the reference, on the right
						else if(btn.getYPos() == selectedBtns.get(selectedBtns.size()-1).getYPos()+1  && btn.getXPos() == selectedBtns.get(selectedBtns.size()-1).getXPos()){
							word = word + btn.getLetter();
							selectedBtns.add(btn);
						}else{
							clearSelectedBtns();
							selectedBtns.add(btn);
							word = btn.getLetter();
						}
					}
					
				}
			}
			btn.toggle(); //toggle the button to be selected or not
			checkMatch(); //check if a word was found
		}//end if statement for checking if the source is a LetterButton
	}
	
	
	
	/**
	 * Reset the selected buttons so that they are unselected
	 * Clear the word that was currently formed
	 */
	public void clearSelectedBtns(){
		for(LetterButton b:selectedBtns){
			b.setSelected(false);
		}
		selectedBtns.clear();
		word = "";
	}
	
	public int getLength(){
		return length;
	}
}