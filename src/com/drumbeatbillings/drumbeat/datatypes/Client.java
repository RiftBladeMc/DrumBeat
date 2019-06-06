package com.drumbeatbillings.drumbeat.datatypes;

import com.drumbeatbillings.drumbeat.DrumBeat;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Client {
    final Database db;
    private final int id;
    private String first, last, company, email, address;
    private long phone;
    Client(Database db, String first, String last, String company, String email, String address, long phone) throws SQLException {
        this.db = db;
        this.first = first;
        this.last = last;
        this.company = company;
        this.email = email;
        this.address = address;
        this.phone = phone;
        try (Statement stmt = db.connection.createStatement()) {
            stmt.executeUpdate("INSERT INTO DrumBeat ('first', 'last', 'company', 'email', 'address', 'phone') values ('" + first + "', '" + last + "', '"+ company + "', '"+ email + "', '" + address + "', " + phone + ")");
            id = stmt.getGeneratedKeys().getInt(1);
            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS DrumBeat." + id + "(date BIGINT, time INT)");
        }
    }

    Client(Database db, int id) throws SQLException {
        this.db = db;
        this.id = id;

        try (Statement stmt = db.connection.createStatement()) {
            ResultSet res = stmt.executeQuery("SELECT first, last, company, email, address, phone from DrumBeat." + id + " WHERE id=" + id + " LIMIT 1");
            res.next();
            first = res.getString(1);
            last = res.getString(2);
            company = res.getString(3);
            email = res.getString(4);
            address = res.getString(5);
            phone = res.getLong(6);
        }
    }

    public List<Slip> getSlips() {
        try (Statement stmt = db.connection.createStatement()) {
            ResultSet res = stmt.executeQuery("SELECT * FROM DrumBeat." + id);
            List<Slip> slips = new ArrayList<>();
            while (res.next()) {
                slips.add(new Slip(this, res.getLong(1), res.getInt(2)));
            }
            return slips;
        } catch (SQLException sql) {
            DrumBeat.handleError(sql);
        }
        return null;
    }

    public String getFirst() {
        return first;
    }

    public String getLast() {
        return last;
    }

    public String getCompany() {
        return company;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

    public long getPhone() {
        return phone;
    }

    public void setFirst(String first) {
        try (Statement stmt = db.connection.createStatement()) {
            stmt.executeUpdate("UPDATE DrumBeat SET first = '" + first + "' WHERE id=" + id);
            this.first = first;
        } catch (SQLException sql) {
            DrumBeat.handleError(sql);
        }
    }

    public void setLast(String last) {
        try (Statement stmt = db.connection.createStatement()) {
            stmt.executeUpdate("UPDATE DrumBeat SET last = '" + last + "' WHERE id=" + id);
            this.company = last;
        } catch (SQLException sql) {
            DrumBeat.handleError(sql);
        }
    }

    public void setCompany(String company) {
        try (Statement stmt = db.connection.createStatement()) {
            stmt.executeUpdate("UPDATE DrumBeat SET company = '" + company + "' WHERE id=" + id);
            this.company = company;
        } catch (SQLException sql) {
            DrumBeat.handleError(sql);
        }
    }

    public void setEmail(String email) {
        try (Statement stmt = db.connection.createStatement()) {
            stmt.executeUpdate("UPDATE DrumBeat SET email = '" + email + "' WHERE id=" + id);
            this.email = email;
        } catch (SQLException sql) {
            DrumBeat.handleError(sql);
        }
    }

    public void setAddress(String address) {
        try (Statement stmt = db.connection.createStatement()) {
            stmt.executeUpdate("UPDATE DrumBeat SET address = '" + address + "' WHERE id=" + id);
            this.address = address;
        } catch (SQLException sql) {
            DrumBeat.handleError(sql);
        }
    }

    public void setPhone(long phone) {
        try (Statement stmt = db.connection.createStatement()) {
            stmt.executeUpdate("UPDATE DrumBeat SET phone = " + phone + " WHERE id=" + id);
            this.phone = phone;
        } catch (SQLException sql) {
            DrumBeat.handleError(sql);
        }
    }

    public void delete() {
        try (Statement stmt = db.connection.createStatement()) {
            stmt.executeUpdate("DELETE FROM DrumBeat WHERE id=" + id);
        } catch (SQLException sql) {
            DrumBeat.handleError(sql);
        }
    }
}
