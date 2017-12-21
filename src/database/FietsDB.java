package database;

import databag.Fiets;
import database.connect.ConnectionManager;
import datatype.Standplaats;
import datatype.Status;
import exception.DBException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Set;

public class FietsDB implements InterfaceFietsDB {

    @Override
    public Integer toevoegenFiets(Fiets fiets) throws DBException {

        if (fiets != null) {
            Integer primaryKey = null;

            // connectie tot stand brengen (en automatisch sluiten)
            try (Connection conn = ConnectionManager.getConnection();) {
                // preparedStatement opstellen (en automatisch sluiten)
                try (PreparedStatement stmt = conn.prepareStatement(
                        "insert into fiets(registratienummer"
                        + " , opmerkingen"
                        + " , standplaats"
                        + " , status"
                        + " ) values(?,?,?,?)",
                        Statement.RETURN_GENERATED_KEYS);) {
                    stmt.setInt(1, fiets.getRegistratienummer());
                    stmt.setString(2, fiets.getOpmerking());
                    stmt.setString(3, fiets.getStandplaats().name());
                    stmt.setString(4, fiets.getStatus().name());
                    stmt.execute();

                    ResultSet generatedKeys = stmt.getGeneratedKeys();
                    if (generatedKeys.next()) {
                        primaryKey = generatedKeys.getInt(1);
                    }
                } catch (SQLException sqlEx) {
                    throw new DBException("SQL-exception in toevoegenFiets "
                            + "- statement" + sqlEx);
                }
            } catch (SQLException sqlEx) {
                throw new DBException("SQL-exception in toevoegenFiets "
                        + "- connection" + sqlEx);
            }
            return primaryKey;
        } else {
            return null;
        }
    }

    @Override
    public void wijzigenToestandFiets(Integer regnr, Status status) throws DBException {
        if (regnr != null && status != null) {
            

            // connectie tot stand brengen (en automatisch sluiten)
            try (Connection conn = ConnectionManager.getConnection();) {
                // preparedStatement opstellen (en automatisch sluiten)
                try (PreparedStatement stmt = conn.prepareStatement(
                        "Update fiets"
                        + " set status = ?"
                        + " where registratienummer=?");) {
                    stmt.setString(1, status.name());
                    stmt.setInt(2, regnr);
                    stmt.execute();

                } catch (SQLException sqlEx) {
                    throw new DBException("SQL-exception in wijzigenStatusFiets "
                            + "- statement" + sqlEx);
                }
            } catch (SQLException sqlEx) {
                throw new DBException("SQL-exception in wijzigenStatusFiets "
                        + "- connection" + sqlEx);
            }

        }
    }

    @Override
    public void wijzigenOpmerkingFiets(Integer regnr, String opmerking) throws DBException {
        if (regnr != null && opmerking != null) {
            

            // connectie tot stand brengen (en automatisch sluiten)
            try (Connection conn = ConnectionManager.getConnection();) {
                // preparedStatement opstellen (en automatisch sluiten)
                try (PreparedStatement stmt = conn.prepareStatement(
                        "Update fiets"
                        + " set opmerkingen = ?"
                        + " where registratienummer=?");) {
                    stmt.setString(1, opmerking);
                    stmt.setInt(2, regnr);
                    stmt.execute();

                } catch (SQLException sqlEx) {
                    throw new DBException("SQL-exception in wijzigenOpmerkingFiets "
                            + "- statement" + sqlEx);
                }
            } catch (SQLException sqlEx) {
                throw new DBException("SQL-exception in wijzigenOpmerkingFiets "
                        + "- connection" + sqlEx);
            }

        }
    }

    @Override
    public Fiets zoekFiets(Integer regnr) throws DBException {
        Fiets f=null;
        if (regnr != null) {
            

            // connectie tot stand brengen (en automatisch sluiten)
            try (Connection conn = ConnectionManager.getConnection();) {
                // preparedStatement opstellen (en automatisch sluiten)
                try (PreparedStatement stmt = conn.prepareStatement(
                        "Select registratienummer, status, standplaats, opmerkingen"
                        + "From fiets"
                        + " where registratienummer=?");) {
                    stmt.setInt(1, regnr);
                    stmt.execute();
                    
                    try(ResultSet r= stmt.getResultSet()){
                        
                        f=new Fiets();
                        
                        if(r.next()){
                            f.setRegistratienummer(r.getInt("registratienummer"));
                            f.setStatus(Status.valueOf(r.getString("status")));
                            f.setStandplaats(Standplaats.valueOf(r.getString("standplaats")));
                            f.setOpmerking(r.getString("opmerkingen"));                                                  
                        }
                                               
                    }
                    

                } catch (SQLException sqlEx) {
                    throw new DBException("SQL-exception in zoekFiets "
                            + "- statement" + sqlEx);
                }
            } catch (SQLException sqlEx) {
                throw new DBException("SQL-exception in zoekFiets "
                        + "- connection" + sqlEx);
            }

        }
        return f;
    }

    @Override
    public ArrayList<Fiets> zoekAlleFietsen() throws DBException {
              
            ArrayList<Fiets> fietsen = new ArrayList<Fiets>();
           

            // connectie tot stand brengen (en automatisch sluiten)
            try (Connection conn = ConnectionManager.getConnection();) {
                // preparedStatement opstellen (en automatisch sluiten)
                try (PreparedStatement stmt = conn.prepareStatement(
                        "Select registratienummer, status, standplaats, opmerkingen"
                        + "From fiets");) {
                    stmt.execute();
                    
                    try(ResultSet r= stmt.getResultSet()){
                        
                        
                        
                        while (r.next())
                        {
                            Fiets f=new Fiets();
                            f.setRegistratienummer(r.getInt("registratienummer"));
                            f.setStatus(Status.valueOf(r.getString("status")));
                            f.setStandplaats(Standplaats.valueOf(r.getString("standplaats")));
                            f.setOpmerking(r.getString("opmerkingen"));    
                            fietsen.add(f);
                        }
                                               
                    }
                    

                } catch (SQLException sqlEx) {
                    throw new DBException("SQL-exception in zoekAlleFietsen "
                            + "- statement" + sqlEx);
                }
            } catch (SQLException sqlEx) {
                throw new DBException("SQL-exception in zoekAlleFietsen "
                        + "- connection" + sqlEx);
            }

       return fietsen;
       
    }

}
