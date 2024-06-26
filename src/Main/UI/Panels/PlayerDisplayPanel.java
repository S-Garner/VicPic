package src.Main.UI.Panels;

import com.sun.jdi.ArrayReference;
import src.IOClasses.SimpleInstrHolder;
import src.Interfaces.Instructions;
import src.Interfaces.SimpleInstructions;
import src.Main.UI.Format.VicFormatter;
import src.Students.StudentFunctions.RandStudentSelector;
import src.Students.Victim;
import src.UIElements.Buttons.RoundButton;
import src.UIElements.Buttons.RoundedButton;
import src.UIElements.Colors.CurrentUITheme;
import src.UIElements.Colors.Images;
import src.UIElements.Panels.PlayerPanel;
import src.UIElements.Panels.RoundedPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.stream.Collectors;

public class PlayerDisplayPanel {
    //private RoundedPanel mainPanel;

    private JPanel topPanel;
    private VicFormatter topFormat;

    private JPanel holderPanel;

    private GridBagConstraints grid;

    private int rows = 0;
    private int columns = 0;
    private int buffDistance = 5;

    private RoundButton addPlayer;
    private VicFormatter addPlayerFormat;

    private RoundedPanel mainPanel;

    VicFormatter mainFormat;

    private CurrentUITheme theme;

    //private HashMap<>

    ArrayList<PlayerPanel> players = new ArrayList<>();
    ArrayList<Victim> victims = new ArrayList<>();

    Victim tempVic;

    SimpleInstructions addPlayInstr;
    SimpleInstructions removePlayInstr = new SimpleInstructions() {
        @Override
        public void execute() {

        }
    };

    private int panelWidth = 900;
    private int panelHeight = 250;

    public PlayerDisplayPanel(CurrentUITheme thatTheme, ArrayList<Victim> inVic) {
        victims = inVic;
        theme = thatTheme;
        grid = new GridBagConstraints();
        mainPanel = new RoundedPanel(theme);
        topPanel = new JPanel();
        topFormat = new VicFormatter(topPanel, buffDistance);
        mainFormat = new VicFormatter(mainPanel, buffDistance);
        topFormat.getPanel().add(mainPanel);
        holderPanel = new JPanel();
        mainPanel.add(holderPanel);
        holderPanel.setLayout(new GridBagLayout());
        holderPanel.setPreferredSize(new Dimension(panelWidth, panelHeight));

        Images imageGetter = new Images("plus", theme, "UIimage");
        addPlayer = new RoundButton(imageGetter.getImage(), theme);
        addPlayer.setToolTipText("Add Player");
        addPlayerFormat = new VicFormatter(addPlayer, buffDistance);

        mainPanel.setPreferredSize(new Dimension(panelWidth + 20, 690));

        holderPanel.setBackground(null);

        grid.gridx = rows;
        grid.gridy = columns;
        holderPanel.add(addPlayer, grid);

        SimpleInstructions addPlayInstr = this::addPlayerPanel;
        SimpleInstrHolder addPHold = new SimpleInstrHolder(addPlayInstr);

        addPlayer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addPHold.execute();
            }
        });
    }

    public JPanel getTopPanel() {
        return mainPanel;
    }


    public void addPlayerPanel() {
        if (players.size() >= 15) {
            System.out.println("Player limit reached.");
            return;
        }

        PlayerPanel newPlayer = new PlayerPanel(theme);
        newPlayer.setElementNo(players.size());
        newPlayer.changeDisplayName("Player" + players.size());
        players.add(newPlayer);

        // Calculate the position for the new player
        grid.gridx = rows;
        grid.gridy = columns;
        holderPanel.add(newPlayer, grid);

        //addToPick(newPlayer.getVictim());

        // Attach remove action to the remove button of this PlayerPanel
        RoundButton removeButton = newPlayer.getRemoveButton();
        removeButton.addActionListener(e -> removePlayerPanel(newPlayer));

        RoundButton randButton = newPlayer.getRandomButton();
        final PlayerPanel currentNewPlayer = newPlayer;
        randButton.addActionListener(e -> {
            Victim randomVictim;
            do {
                randomVictim = RandStudentSelector.getRandomStudent(victims, players.stream().map(PlayerPanel::getVictim).collect(Collectors.toList()));
            } while (players.contains(randomVictim));  // Check if the randomVictim is already in the players list
            currentNewPlayer.setPlayer(randomVictim);  // Use the final reference here
        });

        RoundButton testButton = newPlayer.getTestButton();
        testButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                newPlayer.resizeComponents(50, 50, 15, 15);
            }
        });

        // Move to the next position
        rows++;
        if (rows > 4) {
            rows = 0;
            columns++;
        }

        if (rows == 0 && columns == 3) {
            holderPanel.remove(addPlayer);
        }

        if (rows == 0 && columns <= 2) {
            int tempHeight = 250;
            int tempValue = columns * 210;
            panelHeight = tempHeight;
            panelHeight += tempValue;
            holderPanel.setPreferredSize(new Dimension(panelWidth, panelHeight));
            //mainPanel.setPreferredSize(new Dimension(panelWidth + 20, panelHeight + 20));
        }

        // Add the addPlayer button in the next available cell
        updateAddButtonPosition();


        if (columns < 2) {
            updatePanelSize();
        }

        holderPanel.revalidate();
        holderPanel.repaint();
    }


    public void removePlayerPanel(PlayerPanel playerToRemove) {
        holderPanel.remove(playerToRemove);
        players.remove(playerToRemove);

        rearrangePlayerPanels();

        // Update the panel size after removing the player, only if columns are less than 2
        if (columns < 2) {
            updatePanelSize();
        }

        updateAddButtonPosition();
        updatePanelSize();

        holderPanel.revalidate();
        holderPanel.repaint();
    }

    private void rearrangePlayerPanels() {
        // Reset the rows and columns
        rows = 0;
        columns = 0;

        for (PlayerPanel player : players) {
            grid.gridx = rows;
            grid.gridy = columns;
            holderPanel.remove(player);
            holderPanel.add(player, grid);

            if (rows >= 4) {
                rows = 0;
                columns++;
            } else {
                rows++;
            }
        }

        updateAddButtonPosition();
        updatePanelSize();
    }

    private void updatePanelSize() {
        // Only resize if we have fewer than 2 columns
        if (columns < 2) {
            // Calculate the required height based on the number of columns (or rows of players)
            int requiredHeight = 250; // Start with the base height
            if (columns > 0 || (columns == 0 && rows > 0)) { // If we have one full column or started a new row
                requiredHeight += 210 * columns;
            }

            // Set the new calculated height
            panelHeight = requiredHeight;
        }

        // Apply the determined dimensions
        holderPanel.setPreferredSize(new Dimension(panelWidth, panelHeight));
        //mainPanel.setPreferredSize(new Dimension(panelWidth + 20, panelHeight + 20));

        // Revalidate and repaint the holderPanel to apply the changes
        holderPanel.revalidate();
        holderPanel.repaint();
    }

    private void updateAddButtonPosition() {
        // Check if we need to move to a new row
        if (rows >= 5) {
            rows = 0;
            columns++;
        }

        // Set the new position for the addPlayer button
        grid.gridx = rows;
        grid.gridy = columns;
        if (columns <= 2) {
            holderPanel.add(addPlayer, grid);
        }

        // Revalidate and repaint the holderPanel to apply the changes
        holderPanel.revalidate();
        holderPanel.repaint();
    }

    public void addToPick(Victim inVic){
        inVic.setNumPicked(1);
    }

    public ArrayList<PlayerPanel> getPlayers(){
        return players;
    }

}