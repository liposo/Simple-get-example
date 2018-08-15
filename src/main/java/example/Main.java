package example;

import example.pojos.Post;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class Main {

    private JFrame mainFrame;
    private JTextArea outputTextArea;
    private JPanel panelSouth;
    private JPanel panelNorth;
    private JLabel userIdLabel;
    private JTextField userIdInput;
    private JButton requestPostsButton;
    private JScrollPane scroll;

    private static ApiConnect apiConnect;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Main window = new Main();
                    window.mainFrame.setVisible(true);

                    apiConnect = new ApiConnect();
                } catch(Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public Main() {
        initialize();
    }

    private void initialize() {

        mainFrame = new JFrame();
        mainFrame.setResizable(true);
        mainFrame.setTitle("Simple REST GET example");
        mainFrame.setBounds(100, 100, 800, 800);
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.getContentPane().setLayout(new BorderLayout(0, 0));

        outputTextArea = new JTextArea();
        panelSouth = new JPanel();
        panelNorth = new JPanel();
        userIdLabel = new JLabel("Select a user id[1, 2 ,3, 4]:");
        userIdInput = new JTextField(4);
        requestPostsButton = new JButton("Get Posts");

        scroll = new JScrollPane(outputTextArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);


        panelNorth.setLayout(new FlowLayout(FlowLayout.CENTER, 15, 15));
        mainFrame.getContentPane().add(panelNorth, BorderLayout.NORTH);


        panelSouth.setLayout(new BorderLayout());
        mainFrame.getContentPane().add(panelSouth, BorderLayout.CENTER);


        panelNorth.add(userIdLabel);
        panelNorth.add(userIdInput);
        panelNorth.add(requestPostsButton);

        outputTextArea.setEditable(false);
        outputTextArea.setVisible(true);
        outputTextArea.setRows(30);
        outputTextArea.setAutoscrolls(true);
        outputTextArea.setBackground(Color.lightGray);
        outputTextArea.setLineWrap(true);
        outputTextArea.setWrapStyleWord(true);


        panelSouth.add(scroll);
        mainFrame.pack();

        requestPostsButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                outputTextArea.setText(null);

                showPosts();

            }
        });
    }

    private void showPosts() {
        try {
            Post[] posts = apiConnect.getPosts(userIdInput.getText());

            if(posts.length == 0) {
                outputTextArea.setText(null);
                outputTextArea.setText("No published posts");
            }

            for(Post post : posts) {
                String postString = "Post id: " + post.getId()+"\n" + "Post Title: " + post.getTitle() + "\n" + "Post Body: " + post.getBody() + "\n" + "-----------\n";

                outputTextArea.append(postString);
            }
        } catch (Exception exception) {
            outputTextArea.setText(null);
            outputTextArea.setText(exception.toString());
        }

    }
}