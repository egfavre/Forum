package com.egfavre;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    //refactoring - add methods
    public static ArrayList<Post> parsePosts(String filename) throws FileNotFoundException {
        ArrayList<Post> posts = new ArrayList<>();
        File f = new File(filename);
        Scanner fileScanner = new Scanner (f);
        while (fileScanner.hasNextLine()) {
            String line = fileScanner.nextLine();
            String[] columns = line.split("\\|");
            System.out.println();
            Post post = new Post(Integer.valueOf(columns[0]), columns[1], columns[2]);
            posts.add(post);
        }
        return posts;
    }

    public static void printPosts(ArrayList<Post> posts, int currentPost) {
        int postId = 0;
        for (Post post : posts){
            if (post.replyId == currentPost){
                System.out.printf("[%s] %s by %s\n", postId, post.text, post.author);
                //at this point still doesn't display index # but can loop and get replies- added this loop after putting in sout type id...
            }
            postId ++;
        }
    }

    public static void main(String[] args) throws FileNotFoundException {

        ArrayList<Post> posts = parsePosts("posts.txt");
        Scanner consoleScanner = new Scanner(System.in);
        int currentPost = -1;

        while (true) {
            printPosts(posts,currentPost);
            System.out.println("type id number of the post to which you want to see replies");
            currentPost = Integer.valueOf(consoleScanner.nextLine());
        }
    }
}
