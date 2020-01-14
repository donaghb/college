package com.clubexplorer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.ColumnTextAlign;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.server.PWA;
import com.vaadin.flow.router.Route;

/**
 * The main view contains a button and a click listener.
 */
@Route
@PWA(name = "My Application", shortName = "My Application")
public class MainView extends VerticalLayout {
    
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    Connection conn;
    
    final String jdbcConnectionString = 
                    "jdbc:sqlserver://clubdbtest.database.windows.net:1433;" + // Change to your server name
                    "database=ClubDB;" + 
                    "user=admin-club-db@clubdbtest;" + 
                    "password=Bikesrgr8;" +
                    "encrypt=true;" + 
                    "trustServerCertificate=false;" +
                    "hostNameInCertificate=*.database.windows.net;" + 
                    "loginTimeout=30;";
    
    
    public MainView() {
        add(new H2("🚲Cycling Club Events"));
        add(new H3("Members"));

        HorizontalLayout hpanel = new HorizontalLayout();
        try
        {
            DriverManager.registerDriver(new com.microsoft.sqlserver.jdbc.SQLServerDriver());
            conn = DriverManager.getConnection(jdbcConnectionString); 
            add(new H3("Cyclist"));
            ResultSet rsCyclistNames = conn.createStatement().executeQuery("select * from Cyclists");
            ArrayList<Cyclist> cyclists = new ArrayList<Cyclist>();

            while (rsCyclistNames.next()) 
            {
                Cyclist c = new Cyclist();
                c.setId(rsCyclistNames.getInt("id"));
                c.setGender(rsCyclistNames.getString("gender"));
                c.setAge(rsCyclistNames.getInt("age"));
                c.setName(rsCyclistNames.getString("name"));
                cyclists.add(c);
                
                
            }
            Grid<Cyclist> CyclistGrid = new Grid<>(Cyclist.class);
            CyclistGrid.removeAllColumns();
            CyclistGrid.addColumn(Cyclist::getName).setHeader("Member Name").setSortable(true)
                    .setTextAlign(ColumnTextAlign.START);
            CyclistGrid.addColumn(Cyclist::getAge).setHeader("Member Age").setSortable(true)
                    .setTextAlign(ColumnTextAlign.CENTER);
            CyclistGrid.addColumn(Cyclist::getGender).setHeader("Member Gender").setSortable(true)
                    .setTextAlign(ColumnTextAlign.START);
            CyclistGrid.setItems(cyclists);
            CyclistGrid.setWidth("50%");
            add(CyclistGrid);
            add(new H3("Events"));
            ResultSet rsEventNames = conn.createStatement().executeQuery("select * from Events");
            ArrayList<Event> events = new ArrayList<Event>();

            while (rsEventNames.next()) 
            {
                Event ev = new Event();
                ev.setTitle(rsEventNames.getString("title"));
                ev.setCost(rsEventNames.getString("cost"));
                ev.setDate(rsEventNames.getDate("date"));
				ev.setDistance(rsEventNames.getInt("distance"));
                events.add(ev);
                
                
            }
            Grid<Event> EventGrid = new Grid<>(Event.class);
            EventGrid.removeAllColumns();
            EventGrid.addColumn(Event::getTitle).setHeader("Event").setSortable(true)
                    .setTextAlign(ColumnTextAlign.CENTER);
            EventGrid.addColumn(Event::getCost).setHeader("Event Cost").setSortable(true)
                    .setTextAlign(ColumnTextAlign.START);
			EventGrid.addColumn(Event::getDate).setHeader("Event Date").setSortable(true)
                    .setTextAlign(ColumnTextAlign.START);
			EventGrid.addColumn(Event::getDistance).setHeader("Event Distance").setSortable(true)
                    .setTextAlign(ColumnTextAlign.START);
            EventGrid.setItems(events);
            EventGrid.setWidth("50%");
            add(EventGrid);
        }

        catch(SQLException e)
        {
            Dialog d = new Dialog(); 
            d.add(new H4(e.getMessage()));
            d.open();
        }

       add(hpanel);
    }
}
