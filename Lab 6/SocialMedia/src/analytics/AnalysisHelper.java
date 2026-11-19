/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analytics;

/**
 *
 * @author harshalneelkamal
 * @author Krishiraj Bhagat (Lab 6: implemented the 7 analysis requirements)
 */

import data.DataStore;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import model.Comment;
import model.Post;
import model.User;


public class AnalysisHelper {

    private static final int TOP = 5;

    //Q1 - Find Average number of likes per comment.
    public void getAverageLikesPerComments() {
        Map<Integer, Comment> comments = DataStore.getInstance().getComments();
        int likeNumber = 0;
        int commentNumber = comments.size();
        for (Comment c : comments.values()) {
            likeNumber += c.getLikes();
        }
        if (commentNumber == 0) {
            System.out.println("Q1 - Average number of likes per comment: no comments found");
            return;
        }
        System.out.println("Q1 - Average number of likes per comment: "
                + String.format("%.2f", (double) likeNumber / commentNumber));
    }

    //Q2 - Find the post with most liked comments (the post that has the comment with the most likes).
    public void getMaxLikeCommentPost() {
        DataStore data = DataStore.getInstance();
        Comment commentWithMaxLikes = null;
        for (Comment c : data.getComments().values()) {
            if (commentWithMaxLikes == null || c.getLikes() > commentWithMaxLikes.getLikes()) {
                commentWithMaxLikes = c;
            }
        }
        if (commentWithMaxLikes == null) {
            System.out.println("Q2 - Post with most liked comments: no comments found");
            return;
        }
        System.out.println("Q2 - Post with most liked comments: Post " + commentWithMaxLikes.getPostId()
                + " (its comment " + commentWithMaxLikes.getId() + " has " + commentWithMaxLikes.getLikes() + " likes)");
    }

    //Q3 - Find the post with most comments.
    public void getPostWithMostComments() {
        DataStore data = DataStore.getInstance();
        Post postWithMostComments = null;
        for (Post p : data.getPosts().values()) {
            if (postWithMostComments == null || p.getComments().size() > postWithMostComments.getComments().size()) {
                postWithMostComments = p;
            }
        }
        if (postWithMostComments == null) {
            System.out.println("Q3 - Post with most comments: no posts found");
            return;
        }
        System.out.println("Q3 - Post with most comments: Post " + postWithMostComments.getPostId()
                + " (" + postWithMostComments.getComments().size() + " comments)");
    }

    /** @return a count of 0 for every user, so users with no activity are included too */
    private Map<Integer, Integer> emptyCounts() {
        Map<Integer, Integer> counts = new HashMap<>();
        for (Integer userId : DataStore.getInstance().getUsers().keySet()) {
            counts.put(userId, 0);
        }
        return counts;
    }

    private void add(Map<Integer, Integer> counts, int userId, int amount) {
        Integer current = counts.get(userId);
        counts.put(userId, (current == null ? 0 : current) + amount);
    }

    /** prints the first 5 users of the list with their count */
    private void printTop(String title, List<User> users, Map<Integer, Integer> counts, String label) {
        System.out.println(title);
        for (int i = 0; i < TOP && i < users.size(); i++) {
            User u = users.get(i);
            System.out.println("   " + (i + 1) + ". " + u.getFirstName() + " " + u.getLastName()
                    + " (User " + u.getId() + ") - " + label + ": " + counts.get(u.getId()));
        }
    }

    //Q4 - Top 5 inactive users based on total posts number.
    public void getPassiveUsers() {
        DataStore data = DataStore.getInstance();
        Map<Integer, Integer> postNumbers = emptyCounts();
        for (Post p : data.getPosts().values()) {
            add(postNumbers, p.getUserId(), 1);
        }
        List<User> users = new ArrayList<>(data.getUsers().values());
        Collections.sort(users, new UserMapComparator(postNumbers, true));
        printTop("Q4 - Top 5 inactive users based on total posts:", users, postNumbers, "Post count");
    }

}
