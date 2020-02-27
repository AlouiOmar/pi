/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IServices;

import entities.Velo;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Raef
 */
public interface IseviceVelo {

    public void addVelo(Velo p) throws SQLException;

    public List<Velo> getVelos() throws SQLException;

    public Velo getVeloById(int ID) throws SQLException;

    public List<Velo> getVeloByCategorie(String category) throws SQLException;

    public void UpdateVelo(Velo p) throws SQLException;

    public void deleteVelo(Velo v) throws SQLException;

    public List<Velo> FiltrerVeloByCategorie(String category) throws SQLException;

    public List<Velo> FiltrerVeloByprix(float f1, float f2) throws SQLException;
}
