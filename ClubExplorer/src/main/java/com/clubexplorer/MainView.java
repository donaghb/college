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

            add(new H3("Entries"));
            ResultSet rsEntries = conn.createStatement().executeQuery("SELECT * FROM Cyclists INNER JOIN Entries ON Cyclists.id = cyclists.id INNER JOIN Events ON event_id  = Events.id ORDER BY Entries.cyclist_id");
            ArrayList<Entry> entries = new ArrayList<Entry>();

            while (rsEntries.next()) 
            {
                Entry et = new Entry();
                et.setId(rsEntries.getInt("id"));
                et.setGender(rsEntries.getString("gender"));
                et.setAge(rsEntries.getInt("age"));
                et.setName(rsEntries.getString("name"));
				et.setTitle(rsEntries.getString("title"));
                et.setCost(rsEntries.getString("cost"));
                et.setDate(rsEntries.getDate("date"));
				et.setDistance(rsEntries.getInt("distance"));
				et.setCyclist_id(rsEntries.getInt("cyclist_id"));
				et.setEvent_id(rsEntries.getInt("event_id"));
                entries.add(et);
                
                
            }
            Grid<Entry> EntryGrid = new Grid<>(Entry.class);
            EntryGrid.removeAllColumns();
			EntryGrid.addColumn(Entry::getId).setHeader("ID").setSortable(true)
                    .setTextAlign(ColumnTextAlign.START);
            EntryGrid.addColumn(Entry::getName).setHeader("Member Name").setSortable(true)
                    .setTextAlign(ColumnTextAlign.START);
            EntryGrid.addColumn(Entry::getAge).setHeader("Member Age").setSortable(true)
                    .setTextAlign(ColumnTextAlign.CENTER);
            EntryGrid.addColumn(Entry::getGender).setHeader("Member Gender").setSortable(true)
                    .setTextAlign(ColumnTextAlign.START);
			EntryGrid.addColumn(Entry::getTitle).setHeader("Event").setSortable(true)
                    .setTextAlign(ColumnTextAlign.CENTER);
            EntryGrid.addColumn(Entry::getCost).setHeader("Event Cost").setSortable(true)
                    .setTextAlign(ColumnTextAlign.START);
			EntryGrid.addColumn(Entry::getDate).setHeader("Event Date").setSortable(true)
                    .setTextAlign(ColumnTextAlign.START);
			EntryGrid.addColumn(Entry::getDistance).setHeader("Event Distance").setSortable(true)
                    .setTextAlign(ColumnTextAlign.START);
			EntryGrid.addColumn(Entry::getCyclist_id).setHeader("Cyclist ID").setSortable(true)
                    .setTextAlign(ColumnTextAlign.START);
			EntryGrid.addColumn(Entry::getEvent_id).setHeader("Event ID").setSortable(true)
                    .setTextAlign(ColumnTextAlign.START);
            EntryGrid.setItems(entries);
            EntryGrid.setWidth("50%");
            add(EntryGrid);
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
