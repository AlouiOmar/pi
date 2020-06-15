/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pi_javafx.userinterface;

import java.sql.SQLException;
import java.util.List;
import pi_javafx.userentites.Post;

/**
 *
 * @author USER
 */
public interface IServicePost {
    
     public void addPost(Post p) throws SQLException;

    public List<Post> getPosts() throws SQLException;

   

    public void deletePost(Post p) throws SQLException;

   

    public void updatePost(Post p) throws SQLException;
    
     

    
    public List<Post> RechercherPost(String nom) throws SQLException;
    
    public List<Post> getTrier()  throws SQLException ;

    
}
