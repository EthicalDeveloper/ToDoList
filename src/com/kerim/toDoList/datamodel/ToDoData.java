package com.kerim.toDoList.datamodel;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;

public class ToDoData {
    private static ToDoData instance = new ToDoData();
    private static String filename = "ToDoListItems.txt";

    private ObservableList<ToDoList> toDoItems;
    private DateTimeFormatter formatter;

    public static ToDoData getInstance(){
        return instance;
    }

    private ToDoData(){
        formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    }

    public ObservableList<ToDoList> getToDoItems(){
        return toDoItems;
    }

    public void addToDoItem(ToDoList item){
        toDoItems.add(item);
    }


    public void loadToDoItems() throws IOException{
        toDoItems = FXCollections.observableArrayList();
        Path path = Paths.get(filename);
        BufferedReader br = Files.newBufferedReader(path);

        String input;

        try{
            while((input = br.readLine()) != null){
                String[] itemPieces = input.split("\t");

                String shortDescription = itemPieces[0];
                String details = itemPieces[1];
                String dateString = itemPieces[2];

                LocalDate date = LocalDate.parse(dateString, formatter);
                ToDoList toDoList = new ToDoList(shortDescription,details,date);
                toDoItems.add(toDoList);


            }


        }finally {
            if(br!=null){
                br.close();
            }
        }
    }

    public void storeToDoList() throws IOException{
        Path path = Paths.get(filename);
        BufferedWriter bw = Files.newBufferedWriter(path);

        try{
            Iterator<ToDoList> iter = toDoItems.iterator();
            while(iter.hasNext()){
                ToDoList item = iter.next();
                bw.write(String.format("%s\t%s\t%s\t", item.getShortDescription(), item.getDetails(), item.getDeadline().format(formatter)));
                bw.newLine();
            }
        }finally {
            if(bw != null){
                bw.close();
            }
        }

    }

    public void deleteToDoItem(ToDoList item){
        toDoItems.remove(item);
    }



}
