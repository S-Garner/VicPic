![colorDemo](https://github.com/S-Garner/VicPic/assets/143762033/c25fbdcd-322e-4805-8da7-1c8085d41b3e)# VicPic

* Under construction *

Forgive the terrible imports, will fix later on:

Geneal Ideas:

  "Custom UI":
  
	  I wanted to create a sort of simplified UI look and approach. So far, I have created a button, panel, and textPane class that extend from their JComponents respectively. Every type has a constructor that will take something called "CurrentUITheme"
      CurrentUITheme:
        This has two objects called currentBackgroundColor, currentForegroundColor: These two objects are members of a record class called "ColorScheme". From what I have read, using a record approach would the best way to store UI colors, because instead of storing all the RGB values in a seperate file, all you need to do is store the word associated with the color in a simple text file (In the example, I have one called UITheme.txt) This file has only two lines: "Foreground:" and "Background:".
        The basic form of the system:
          * Input a 'keyword' into the textfile associated to the available colors (Located in UIColors)
            - The system will check the 'keyword' and will go to UIColors and assign the CurrentUITheme.currentBackgroundColor and .currentForegroundColor to what ever the keyword colors are associated with. The reason I chose records was because it allows the program to store associated colors for certain UI actions (Main, Select, Action) these are different colors for the different status of the UI elements *I currently have only implemented it into the RoundedButton class, when you move over the button and when you click, the button will change colors showing the different status.
              - The available colors (will add more later on)
                - charcoal
                - cream
                - moss
                - navy
                - marigold
                - crimson
        - After the respective colors have been chosen, they are stored in the CurrentUITheme.
      - Now that we have the colors, we can take the current ui theme, and pass it to all of the UI elements in the program.
  * Why so compclicated?
    - This method allows for a system change throughout the entire program just by simply typing in whatever color we want (Example below)
    - Currently, there is a mock up of what the general idea of how this system works, though it is very rough. I am not married to this idea, so if you hate it, that's fine, we don't have to use it. Just wanted a simple way to store a general look and feel without having to manually change all of the UI elements in the project.
    - ![colorDemo](https://github.com/S-Garner/VicPic/assets/143762033/1b061dfb-7470-4462-b361-64263c915b8f)



"Buttons"

  In the project there are two called 'Instructions' and 'Trigger' and two classes called 'CompInstrHolder' and 'SignalAndStart'. Because of the number of people in our group, a common issue can be connecting button actions with affecting specific things in a project. To mitigate this, I am trying to make a system of "Cause and Effect" plug and play. The system consist of object to change, how you want it to be changed, what the signal of that change will be, and what will make that signal:
    - Object: Whether it be a textPane, an array, or a file; Whatever object you want to make a change. To do this, we will need something that will take in whatever type of object
    - Instruction: This interface will be the basis for the "Instructions" or how we want the object we are interested in changes. It takes in a generic object so that the object we are interested in can be virtually anything
    - CompInstrHolder: This class will act as a holder for our object (called a component in the class) and the instructions (what you want the object to do)
    - Trigger: This acts as the signal to execute the code; This will act as a connector for the CompInstrHolder (Object and Action) and the component (in this case a button).
    - SignalAndStart: Implements the 'Trigger' interface. This holds a function (compInstrHolder.update()) this is where we initiate the 'Instructions' that will affect the 'Object' we want to change.
    - And finally, take the object you want (Button) and add a new ActionListener(). Assign the SignalAndStart, and that's all
  * Example:
      -
        // NOTICE: I don't like using lambdas, my smooth brain has issues understanding and implementing them, however, the lambda versions of the Instructions and ActionListeners are under the anonymous classes

        // This is the object were interested in changing
        TextCanvas pane = new TextCanvas(theme);
        pane.setSize(new Dimension(250, 100));
        pane.setText("Text");
    
        RoundedButton randomButton = new RoundedButton("Random", theme); // This will be the triggerer

        // How we want the object (pane) to change
        Instructions<TextCanvas> paneUpdate = new Instructions<TextCanvas>() {
            @Override
            public void update(TextCanvas component) {
                component.setText("");
                Student pickedStudent = RandStudentSelector.getRandomStudent(students);
                component.setText("Chosen: " + pickedStudent.getName().getFirstName() + "\n");
            }
        };
        // Instructions<TextCanvas> paneUpdate = component -> {
            Document doc = component.getDocument();
            component.setText("");
            Student pickedStudent = RandStudentSelector.getRandomStudent(students);
            component.setText("Chosen: " + pickedStudent.getName().getFirstName() + "\n");
           };
        
        // This where you connect the two (Object and Instruction)
        CompInstrHolder<TextCanvas> compInstrHolder = new CompInstrHolder<>(pane, paneUpdate);

        // Assign our holder to one place
        Trigger start = new SignalAndStart(compInstrHolder);

        // Finally, link this to our button
        randomButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                start.execute();
            }
        });
        // randomButton.addActionListener(e -> start.execute());

      - So, our button is done. When the actionListener has been triggered, it will perform it's 'execute' function on the compInstrHolder which is the update(). This will then go to the CompInstrHolder, and perform an update on the Object which is determined by the Instructions we want to do
   
  - Why use it instead of just changing the actionListener for the button?
    * This method will allow buttons to have multiple functions
    * Keeps things like ui elements out of Logic side of the code
    * Can be used to link a chain of functions without directly affecting classes and objects
    * Giving two buttons the same function without having to write each button or creating a class for multiple buttons
    * Encapsulation of instructions; We can have instructions take in a function that another class may provide
    * Instructions can passed along as a parameter and can be returned as a result. No need to create classes and statics to perform one function and less parameters needing to be passed.

- I'll try to show a bit more after break and polish it up










    
