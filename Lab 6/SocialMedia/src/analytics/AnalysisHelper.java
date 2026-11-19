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

}
