package com.ucc.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import com.ucc.connection.DatabaseConnection;
import com.ucc.model.Actor;

public class ActorRepository implements IRepository {
    
    private Connection getConnection() throws SQLException { 
        return DatabaseConnection.getInstanceConnection(); 
    }

    @Override
    public List<Actor> findAll() throws SQLException {
        List<Actor> list = new ArrayList<>();
        String query = "SELECT actor_id, first_name, last_name FROM sakila.actor";
        try (Connection conn = getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(query)) {
            while (rs.next()) {
                Actor a = new Actor();
                a.setActor_id(rs.getInt("actor_id"));
                a.setFirst_name(rs.getString("first_name"));
                a.setLast_name(rs.getString("last_name"));
                list.add(a);
            }
        }
        return list;
    }

    @Override
    public Actor save(Actor actor) throws SQLException {
        String query = "INSERT INTO sakila.actor(first_name, last_name) VALUES (?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, actor.getFirst_name());
            ps.setString(2, actor.getLast_name());
            int affected = ps.executeUpdate();
            if (affected > 0) {
                try (ResultSet keys = ps.getGeneratedKeys()) {
                    if (keys.next()) actor.setActor_id(keys.getInt(1));
                }
            }
        }
        return actor;
    }
}
