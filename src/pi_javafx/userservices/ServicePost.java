/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pi_javafx.userservices;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import pi_javafx.userentites.Post;
import pi_javafx.userentites.fos_user;
import pi_javafx.userinterface.IServicePost;
import pi_javafx.utile.Connection;


/**
 *
 * @author USER
 */
public class ServicePost implements IServicePost {
  private Connection cnx;
  
  
    @Override
    public void addPost(Post p) throws SQLException {
           try {
            
            
            String req="INSERT INTO post (title, description, photo, postdate, creator, rating, nbrpost) VALUES (?,?,?,?,?,?,?)";
             PreparedStatement ps=Connection.getInstance().getConnection().prepareStatement(req);
   
            ps.setString(1,p.getTitle());
            ps.setString(2,p.getDescription());
            ps.setString(3,p.getPhoto());
            ps.setString(4,p.getPostdate());
            ps.setInt(5,p.getCreator());
            ps.setInt(6,p.getRating());
            ps.setInt(7,p.getNbrpost());
           
          
//            fis= new FileInputStream(file);
            
            
            Alert alert = new  Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("information dialogue");
            alert.setHeaderText(null);
            alert.setContentText("post  "+p.getTitle()+"  est crée avec succées");
            alert.showAndWait();

            ps.executeUpdate();
            
            
            
        } catch (SQLException ex) {
            Logger.getLogger(ServicePost.class.getName()).log(Level.SEVERE, null, ex);
      
    }
    }
    @Override
    public List<Post> getPosts() throws SQLException {
        
        
        List<Post> listpost = new ArrayList<>();
       // UserServices uu=new UserServices();
       
        try {
            String req="SELECT * FROM post";
            Statement s=Connection.getInstance().getConnection().createStatement();
            ResultSet rs=s.executeQuery(req);
            while(rs.next())
            {    
            Post a =new Post();  
            a.setTitle(rs.getString("title"));
            a.setDescription(rs.getString("description"));
            a.setPhoto(rs.getString("photo"));
            a.setPostdate(rs.getString("postdate"));
            a.setCreator(rs.getInt("creator"));
            a.setRating(rs.getInt("rating"));
            a.setNbrpost(rs.getInt("nbrpost"));
               ImageView imag = new ImageView(new Image("file:C:\\xampp\\htdocs\\"+rs.getString("photo"), 50, 50, true, true));
                a.setImg(imag);
            
                listpost.add(a);
            
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServicePost.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listpost;
        
        
        
      
    }

   

    @Override
    public void deletePost(Post p) throws SQLException 
    {
      
            String req="DELETE FROM post WHERE id=?";
            PreparedStatement ps=Connection.getInstance().getConnection().prepareStatement(req);
            ps.setInt(1,p.getId());
            ps.executeUpdate();
    }

   

    @Override
    public void updatePost(Post p) throws SQLException {
        
        
         try {

          Statement s=Connection.getInstance().getConnection().createStatement();
        String query = "UPDATE `post` SET `title` = '"+ p.getTitle()
                + "', `description` = '"+ p.getDescription()
                + "', `photo` = '"+ p.getPhoto()
                + "', `postdate` = '"+ p.getPostdate()
                +"', `creator` = '"+ p.getCreator()
                +"', `rating` = '"+ p.getRating()
                +"', `nbrpost`= '"+ p.getNbrpost()
                + "' WHERE `post`.`id` = "+ p.getId();
                
        s.executeUpdate(query);

            
            
        } catch (SQLException ex) {
            Logger.getLogger(ServicePost.class.getName()).log(Level.SEVERE, null, ex);
        }
        
      
    }

    @Override
    public List<Post> RechercherPost(String title) throws SQLException {
        
          List<Post> listrecherche = new ArrayList<>();
       // UserServices uu=new UserServices();
       
        try {
            String req="SELECT * FROM post WHERE title='"+title+"'";
            Statement s=Connection.getInstance().getConnection().createStatement();
            ResultSet rs=s.executeQuery(req);
            while(rs.next())
            {    
            Post a = new Post();
            a.setTitle(rs.getString("title"));
            a.setDescription(rs.getString("description"));
            a.setPhoto(rs.getString("photo"));
            a.setPostdate(rs.getString("postdate"));
            a.setCreator(rs.getInt("creator"));
            a.setRating(rs.getInt("rating"));
            a.setNbrpost(rs.getInt("nbrpost"));
           
                listrecherche.add(a);
            
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServicePost.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listrecherche;
        
        
        
      
    }

    @Override
    public List<Post> getTrier() throws SQLException {
        
           
        List<Post> listpost = new ArrayList<>();
       // UserServices uu=new UserServices();
       
        try {
           String req = "select * from post ORDER BY title ASC";
            Statement s=Connection.getInstance().getConnection().createStatement();
            ResultSet rs=s.executeQuery(req);
            while(rs.next())
            {    
            Post a =new Post();  
            a.setTitle(rs.getString("title"));
            a.setDescription(rs.getString("description"));
            a.setPhoto(rs.getString("photo"));
            a.setPostdate(rs.getString("postdate"));
            a.setCreator(rs.getInt("creator"));
            a.setRating(rs.getInt("rating"));
            a.setNbrpost(rs.getInt("nbrpost"));
            
                listpost.add(a);
            
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServicePost.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listpost;
        
        
      
    }
    
}
