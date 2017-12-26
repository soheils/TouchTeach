package com.touchteach.touchteach.tools;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Soheil on 8/23/2017.
 */

public class SqlLiteHandler extends SQLiteOpenHelper {
    private static final int VersionNumber = 1;
    private static final String DatabaseName = "CachedUserDatabase";
    private static final String TableName = "UserTable";
    private static final String ID = "id";
    private static final String fname = "fname";
    private static final String lname = "lname";
    private static final String cash = "cash";
    private static final String email = "email";
    private static final String TableName2 = "ClassTable";
    private static final String TableName3 = "SearchTable";
    private static final String ID2 = "id";
    private static final String ID3 = "id";
    private static final String ClassName = "ClassName";
    private static final String Capacity = "Capacity";
    private static final String Instructor = "Instructor";
    private static final String Limitations = "Limitations";
    private static final String Cost = "Cost";
    private static final String Description = "Description";
    private static final String Bio = "Bio";
    private static final String Subject = "Subject";


    public SqlLiteHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }
    public void CreateUserTable(SQLiteDatabase db){
        String CREATE_USERS_TABLE = "CREATE TABLE " + TableName + "("
                + ID + " INTEGER PRIMARY KEY," + fname + " TEXT,"
                + lname + " TEXT,"
                + cash + " TEXT,"
                + email + " TEXT,"
                + Bio + " TEXT,"
                + fname + " TEXT"
                + ")";
        db.execSQL(CREATE_USERS_TABLE);
    }
    public void CreateClassTable(SQLiteDatabase db){
        String CREATE_CLASS_TABLE = "CREATE TABLE " + TableName2 + "("
                + ID2 + " INTEGER PRIMARY KEY," + fname + " TEXT,"
                + ClassName + " TEXT,"
                + Capacity + " TEXT,"
                + Subject + " TEXT,"
                + Instructor + " TEXT,"
                + Limitations + "TEXT,"
                + Cost + "TEXT,"
                + Description + "TEXT"
                + ")";
        db.execSQL(CREATE_CLASS_TABLE);
    }
    public void CreateSearchTable(SQLiteDatabase db){
        String CREATE_CLASS_TABLE = "CREATE TABLE " + TableName3 + "("
                + ID3 + " INTEGER PRIMARY KEY," + fname + " TEXT,"
                + ClassName + " TEXT,"
                + Capacity + " TEXT,"
                + Subject + " TEXT,"
                + Instructor + " TEXT,"
                + Limitations + "TEXT,"
                + Cost + "TEXT,"
                + Description + "TEXT"
                + ")";
        db.execSQL(CREATE_CLASS_TABLE);
    }
    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TableName);
        db.execSQL("DROP TABLE IF EXISTS " + TableName2);
        db.execSQL("DROP TABLE IF EXISTS " + TableName3);
        onCreate(db);
    }
    // Adding new contact
    public void addUser(Users user) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(fname, user.getFirstName()); // Contact Name
        values.put(lname, user.getLastName());
        values.put(cash, user.getCash());
        values.put(email, user.getEmail());
        values.put(Bio, user.getBio() + "///" + user.getSubjects() + "///"+user.getGender());
        db.insert(TableName, null, values);
        db.close();
    }
    public void addClass(Class newclass) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(ClassName, newclass.getTitle() ); // Contact Name
        values.put(Capacity, newclass.getCapacity());
        values.put(Instructor, newclass.getInstructor());
        values.put(Cost, newclass.getCost());
        values.put(Description, newclass.getDescription());
        // Inserting Row10
        //TODO motmaeni in gharar naboode Tablename2 bashe?
        db.insert(TableName, null, values);
        db.close();
    }
    public void addSearch(Class newclass) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(ClassName, newclass.getTitle() ); // Contact Name
        values.put(Capacity, newclass.getCapacity());
        values.put(Instructor, newclass.getInstructor());
        values.put(Cost, newclass.getCost());
        values.put(Description, newclass.getDescription());
        // Inserting Row10
        db.insert(TableName3, null, values);
        db.close();
    }

//    todo uncomment
//    // Getting single contact
//    public Contact getContact(int id) {}
//
//    // Getting All Contacts
//    public List<Contact> getAllContacts() {}
//
//    // Getting contacts Count
//    public int getContactsCount() {}
//    // Updating single contact
//    public int updateContact(Contact contact) {}
//
//    // Deleting single contact
//    public void deleteContact(Contact contact) {}
}
