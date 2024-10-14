package tn.espritclubs.cinquieme_SAE_sept.services;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import tn.espritclubs.cinquieme_SAE_sept.models.User;


public class SQLiteUserManager extends SQLiteOpenHelper {
    private static SQLiteUserManager sqLiteManager;

    private static final String DATABASE_NAME = "cinquiemeSAEseptDB";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "User";
    private static final String COUNTER = "Counter";

    private static final String ID_FIELD = "id";
    private static final String EMAIL_FIELD = "email";
    private static final String FIRSTNAME_FIELD = "firstname";
    private static final String LASTNAME_FIELD = "lastname";
    private static final String GENDER_FIELD = "gender";
    private static final String PASSWORD_FIELD = "password";
    private static final String ROLE_FIELD = "role";
    private static final String BIRTHDAY_FIELD = "birthday";
    private static final String PHONE_NUMBER_FIELD = "phone_number";
    private static final String ABOUT_ME_FIELD = "about_me";
    private static final String PROFILE_PICTURE_FIELD = "profile_picture";

    @SuppressLint("SimpleDateFormat")
    private static final DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy HH:mm:ss");


    public SQLiteUserManager( Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static SQLiteUserManager instanceOfDatabase(Context context) {
        if (sqLiteManager == null) {
            sqLiteManager = new SQLiteUserManager(context);
        }
        return sqLiteManager;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        StringBuilder sql;
        sql = new StringBuilder()
                .append("CREATE TABLE IF NOT EXISTS ")
                .append(TABLE_NAME).append(" (")
                .append(COUNTER).append(" INTEGER PRIMARY KEY AUTOINCREMENT, ")
                .append(ID_FIELD).append(" INT, ")
                .append(EMAIL_FIELD).append(" TEXT UNIQUE, ")
                .append(FIRSTNAME_FIELD).append(" TEXT, ")
                .append(LASTNAME_FIELD).append(" TEXT, ")
                .append(GENDER_FIELD).append(" TEXT, ")
                .append(PASSWORD_FIELD).append(" TEXT, ")
                .append(ROLE_FIELD).append(" TEXT, ")
                .append(BIRTHDAY_FIELD).append(" TEXT, ")
                .append(PHONE_NUMBER_FIELD).append(" TEXT, ")
                .append(ABOUT_ME_FIELD).append(" TEXT, ")
                .append(PROFILE_PICTURE_FIELD)
                .append(" TEXT)")
                .append(";");
        sqLiteDatabase.execSQL(sql.toString());
        System.out.println("table created");
    }

    public void initializeAdminUser() {
        SQLiteDatabase db = this.getWritableDatabase();
        User admin = User.getUserForEmail("admin");
        if (admin == null) {
            admin = new User(1, "admin", "admin", "admin", "male", "admin", "admin");
            addUserToDatabase(admin);
        } else {
            System.out.println("Admin already exists");
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
//        switch (oldVersion)
//        {
//            case 1:
//                sqLiteDatabase.execSQL("ALTER TABLE " + TABLE_NAME + " ADD COLUMN " + DELETED_FIELD + " TEXT;");
//                break;
//        }
    }

    public void addUserToDatabase(User user) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(ID_FIELD, user.getId());
        contentValues.put(EMAIL_FIELD, user.getEmail());
        contentValues.put(FIRSTNAME_FIELD, user.getFirstname());
        contentValues.put(LASTNAME_FIELD, user.getLastname());
        contentValues.put(GENDER_FIELD, user.getGender());
        contentValues.put(PASSWORD_FIELD, user.getPassword());
        contentValues.put(ROLE_FIELD, user.getRole());

        try {
            sqLiteDatabase.insert(TABLE_NAME, null, contentValues);
        } catch (Exception e) {
            Log.e("SQLiteError", "Error inserting user: " + e.getMessage());
        }

    }

    public void populateUserListArray() {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();

        try(Cursor result = sqLiteDatabase.rawQuery("SELECT * FROM " + TABLE_NAME, null))
        {
            if (result.getCount() != 0)
            {
                while (result.moveToNext())
                {
                    int id =result.getInt(1);
                    String email = result.getString(2);
                    String firstname = result.getString(3);
                    String lastname = result.getString(4);
                    String gender = result.getString(5);
                    String password = result.getString(6);
                    String role = result.getString(7);
                    String Stringbirthday = result.getString(8);
                    Date birthday = getDateFromString(Stringbirthday);
                    String phoneNumber = result.getString(9);
                    String aboutMe = result.getString(10);
                    //String profilePicture = result.getString(11);

                    User user = new User(id, email, firstname, lastname, gender, password, role, birthday, phoneNumber, aboutMe, "profilePicture");
                    User.userArrayList.add(user);
                }
            }
        }
    }

    public void updateUserInDatabase(User user){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        ContentValues contentValues=new ContentValues();
        contentValues.put(ID_FIELD,user.getId());
        contentValues.put(EMAIL_FIELD,user.getEmail());
        contentValues.put(FIRSTNAME_FIELD,user.getFirstname());
        contentValues.put(LASTNAME_FIELD,user.getLastname());
        contentValues.put(GENDER_FIELD,user.getGender());
        contentValues.put(PASSWORD_FIELD,user.getPassword());
        contentValues.put(ROLE_FIELD,user.getRole());
        contentValues.put(BIRTHDAY_FIELD,getStringFormDate(user.getBirthday()));
        contentValues.put(PHONE_NUMBER_FIELD,user.getPhoneNumber());
        contentValues.put(ABOUT_ME_FIELD,user.getAboutMe());
        contentValues.put(PROFILE_PICTURE_FIELD,user.getProfilePicture());

        sqLiteDatabase.update(TABLE_NAME, contentValues, ID_FIELD + " =? ", new String[]{String.valueOf(user.getId())});
    }

    private String getStringFormDate(Date date) {
        if (date == null) {
            return null;
        }
        return dateFormat.format(date);
    }
    private Date getDateFromString(String string) {
        try {
            return dateFormat.parse(string);
        } catch (ParseException | NullPointerException e) {
            return null;
        }

    }
}
