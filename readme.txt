There are three ways that you can run the game:
        1) If you are using Windows - double click on runmeWNDS.bat This will automatically compile all 
           nescessary classes and will start the program
        2) If you are using Linux - in terminal do: ./runmeLNX.sh 
		This will automatically compile all nescessary classes and will start the program
        3) For any other system:
                1) Open the folder in your command prompt
                2) Compile necessary classes using the following commands:
                        a) javac slots/rungame/*.java
                        b) javac slots/guigame/*.java
                        c) javac slots/exception/*.java
                        d) javac slots/gamelogic/*.java
               3) Run the program by entering slots.rungame.Play



To run the junit test cases use the following commands in your command prompt:
        If you are on Windows:
                double click the runtestWNDS.bat This will automatically compile and run the ReelTest file
	
	If you are on Linux:
		from terminal do: ./runtestLNX.sh
		this will automatically compile and run the ReelTest file
        
        on any other system:
                1) javac -cp .:junit-4.12.jar:hamcrest-core-1.3.jar slots/test/ReelTest.java
                2) java -cp .:junit-4.12.jar:hamcrest-core-1.3.jar org.junit.runner.JUnitCore slots.test.ReelTest
                
                
                

Pictures from: https://pixaroma.deviantart.com/art/Free-Slot-Machine-Icons-478414978 Accessed 02/27/2018

