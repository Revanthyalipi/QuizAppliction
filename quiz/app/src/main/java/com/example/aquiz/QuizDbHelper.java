package com.example.aquiz;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

//import com.example.aquiz.com.example.aquiz.QuizContract.*;

//import com.example.aquiz.Question;
import com.example.aquiz.QuizContract.* ;
//import QuizContract.*;
import java.util.ArrayList;
import java.util.List;


public class QuizDbHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "MyAwesomeQuiz.db";
    private static final int DATABASE_VERSION = 1;

    private static QuizDbHelper instance;

    private SQLiteDatabase db;

    private QuizDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static synchronized QuizDbHelper getInstance(Context context) {
        if (instance == null) {
            instance = new QuizDbHelper(context.getApplicationContext());
        }
        return instance;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        this.db = db;

        final String SQL_CREATE_CATEGORIES_TABLE = "CREATE TABLE " +
                CategoriesTable.TABLE_NAME + "( " +
                CategoriesTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                CategoriesTable.COLUMN_NAME + " TEXT " +
                ")";

        final String SQL_CREATE_QUESTIONS_TABLE = "CREATE TABLE " +
                QuestionsTable.TABLE_NAME + " ( " +
                QuestionsTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                QuestionsTable.COLUMN_QUESTION + " TEXT, " +
                QuestionsTable.COLUMN_OPTION1 + " TEXT, " +
                QuestionsTable.COLUMN_OPTION2 + " TEXT, " +
                QuestionsTable.COLUMN_OPTION3 + " TEXT, " +
                QuestionsTable.COLUMN_ANSWER_NR + " INTEGER, " +
                QuestionsTable.COLUMN_DIFFICULTY + " TEXT, " +
                QuestionsTable.COLUMN_CATEGORY_ID + " INTEGER, " +
                "FOREIGN KEY(" + QuestionsTable.COLUMN_CATEGORY_ID + ") REFERENCES " +
                CategoriesTable.TABLE_NAME + "(" + CategoriesTable._ID + ")" + "ON DELETE CASCADE" +
                ")";

        db.execSQL(SQL_CREATE_CATEGORIES_TABLE);
        db.execSQL(SQL_CREATE_QUESTIONS_TABLE);
        fillCategoriesTable();
        fillQuestionsTable();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + CategoriesTable.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + QuestionsTable.TABLE_NAME);
        onCreate(db);
    }

    @Override
    public void onConfigure(SQLiteDatabase db) {
        super.onConfigure(db);
        db.setForeignKeyConstraintsEnabled(true);
    }

    private void fillCategoriesTable() {
        Category c1 = new Category("Chemistry");
        insertCategory(c1);
        Category c2 = new Category("Physics");
        insertCategory(c2);
        Category c3 = new Category("Math");
        insertCategory(c3);
    }

    public void addCategory(Category category) {
        db = getWritableDatabase();
        insertCategory(category);
    }

    public void addCategories(List<Category> categories) {
        db = getWritableDatabase();

        for (Category category : categories) {
            insertCategory(category);
        }
    }

    private void insertCategory(Category category) {
        ContentValues cv = new ContentValues();
        cv.put(CategoriesTable.COLUMN_NAME, category.getName());
        db.insert(CategoriesTable.TABLE_NAME, null, cv);
    }

    private void fillQuestionsTable() {
        Question q1 = new Question("Which of the following is Ramanujan number",
                "1729", "1738", "1728", 1,
                Question.DIFFICULTY_EASY, Category.MATH);
        insertQuestion(q1);
        Question q7 = new Question("The average of first 50 natural numbers is …………. . ",
                "25.5", "25.30", "25.0", 1,
                Question.DIFFICULTY_EASY, Category.MATH);
        insertQuestion(q7);
        Question q8 = new Question("The period of | sin (3x) | is",
                "2 π", "2 π / 3", "π / 3", 3,
                Question.DIFFICULTY_EASY, Category.MATH);
        insertQuestion(q8);

        Question q2 = new Question("If Log x (1 / 8) = - 3 / 2, then x is equal to",
                "-4", "4", "1/4", 2,
                Question.DIFFICULTY_MEDIUM, Category.MATH);
        insertQuestion(q2);
        Question q3 = new Question("The period of 2 sin x cos x is",
                "3pi", "4pi^2", "pi", 3,
                Question.DIFFICULTY_MEDIUM, Category.MATH);
        insertQuestion(q3);
        Question q4 = new Question("If f(x) is an odd function, then | f(x) | is",
                "an even function", "an odd function", "none of the above", 1,
                Question.DIFFICULTY_EASY, Category.MATH);
        insertQuestion(q4);
        Question q5 = new Question("The period of 2 sin x cos x is ",
                "2 π", "4 π", "π", 3,
                Question.DIFFICULTY_EASY,Category.MATH);
        insertQuestion(q5);
        Question q6 = new Question("If f(x) is an odd function, then | f(x) | ",
                "an odd function", "an even function", "even and odd", 2,
                Question.DIFFICULTY_MEDIUM,Category.MATH);
        insertQuestion(q6);

        Question q9 = new Question("The number of 3-digit numbers divisible by 6, is ………….. ",
                "150", "149", "151", 1,
                Question.DIFFICULTY_MEDIUM,Category.MATH);
        insertQuestion(q9);
        Question q10 = new Question("The period of 2 sin x cos x is",
                "2 π", "π", "4 π", 2,
                Question.DIFFICULTY_MEDIUM, Category.MATH);
        insertQuestion(q10);

        Question q11 = new Question("106 × 106 – 94 × 94 = ?",
                "2004 ", "2400", "2450", 2,
                Question.DIFFICULTY_HARD, Category.MATH);
        insertQuestion(q11);
        Question q12 = new Question(" Which of the following numbers gives 240 when added to its own square?",
                "15", "16", "18", 1,
                Question.DIFFICULTY_HARD, Category.MATH);
        insertQuestion(q12);
        Question q13 = new Question("The simplest form of 1.5 : 2.5 is ……………",
                "6:10", "0.75:1.25", "3:5", 3,
                Question.DIFFICULTY_HARD, Category.MATH);
        insertQuestion(q13);
        Question q14 = new Question("If f(x) is an odd function, then | f(x) | is",
                "an odd function", "an even function", "neither odd nor even", 2,
                Question.DIFFICULTY_HARD, Category.MATH);
        insertQuestion(q14);
        Question q15 = new Question("Evaluation of 8^3 × 8^2 × 8^-5 is ………….",
                "1", "0", "8", 1,
                Question.DIFFICULTY_HARD, Category.MATH);
        insertQuestion(q15);


        Question q16 = new Question("out of the following, which is not emitted by radioactive substance?",
                "Electrons", "Neutrons", "Alpha particles", 3,
                Question.DIFFICULTY_EASY, Category.PHYSICS);
        insertQuestion(q16);
        Question q17 = new Question("Magnetism at the centre of a bar magnet is",
                "minimum", "zero", "maximum", 2,
                Question.DIFFICULTY_EASY, Category.PHYSICS);
        insertQuestion(q17);

        Question q18 = new Question("Banking of road prevents",
                "sliding of vehicle", "rolling of vehicle", "skidding of vehicle", 3,
                Question.DIFFICULTY_EASY, Category.PHYSICS);
        insertQuestion(q18);

        Question q19 = new Question("Who was the first scientist to discover Electrons",
                "Newton", "J.J Thompson", "J.C Bose", 2,
                Question.DIFFICULTY_MEDIUM, Category.PHYSICS);
        insertQuestion(q19);

        Question q20 = new Question("SI unit of momentum is",
                "newton second (Ns)", "θ", "μ", 1,
                Question.DIFFICULTY_MEDIUM, Category.PHYSICS);
        insertQuestion(q20);

        Question q21 = new Question("Maximum value of friction is",
                "limiting friction", "unlimited friction", "infinity", 1,
                Question.DIFFICULTY_EASY, Category.PHYSICS);

        insertQuestion(q21);
        Question q22 = new Question("Generators at a power station produce electric power at voltage ",
                "45 kW", "50kW", "25kW", 3,
                Question.DIFFICULTY_EASY,Category.PHYSICS);
        insertQuestion(q22);

        Question q23 = new Question("Equation which measures alternating voltage is",
                "Vsin ω", "Vo sinωt", "sin t", 2,
                Question.DIFFICULTY_MEDIUM,Category.PHYSICS);
        insertQuestion(q23);

        Question q24 = new Question("In transformer, alternating current is induced in",
                "primary coil", "secondary coil", "iron coil", 2,
                Question.DIFFICULTY_MEDIUM,Category.PHYSICS);
        insertQuestion(q24);

        Question q25 = new Question("Which of the following is the commercial unit of electric power?",
                "joule", "kWh", "Watt", 2,
                Question.DIFFICULTY_MEDIUM, Category.PHYSICS);
        insertQuestion(q25);

        Question q26 = new Question("What is the refractive index of water?",
                "1.00 ", "1.5", "1.33", 3,
                Question.DIFFICULTY_HARD, Category.PHYSICS);
        insertQuestion(q26);

        Question q27 = new Question("The force between charged particles is called as:",
                "Nuclear Force", "Electromagnetic Force", "Gravitational Force", 2,
                Question.DIFFICULTY_HARD, Category.PHYSICS);
        insertQuestion(q27);

        Question q28 = new Question("Which of the following is the cause of echo?",
                "When sound is reflected", "When sound is transmitted", "When sound is absorbed", 1,
                Question.DIFFICULTY_HARD, Category.PHYSICS);
        insertQuestion(q28);

        Question q29 = new Question("The absorption of ink by blotting paper involves",
                "viscosity of ink", "capillary action phenomenon", "siphon action", 2,
                Question.DIFFICULTY_HARD, Category.PHYSICS);
        insertQuestion(q29);

        Question q30 = new Question("On which of the following principles is Hydraulic lift based?",
                "Pascal's law", "Stoke's law", "Bernoulli's principle", 1,
                Question.DIFFICULTY_HARD, Category.PHYSICS);
        insertQuestion(q30);

        Question q31 = new Question("Ratio of hydrogen (H) and oxygen (O) in water molecule by volume is",
                "2: 1 ", "1: 2", "3: 1", 1,
                Question.DIFFICULTY_EASY, Category.CHEMISTRY);
        insertQuestion(q31);

        Question q32 = new Question("What is fourth step of raw water treatment?",
                "Sedimentation", "Coagulation", "Chlorination", 3,
                Question.DIFFICULTY_EASY, Category.CHEMISTRY);
        insertQuestion(q32);

        Question q33 = new Question("Hardness that cannot be removed by boiling is called ?",
                "Temporary hardness", "Permanent hardness", "Both A and B", 2,
                Question.DIFFICULTY_EASY, Category.CHEMISTRY);
        insertQuestion(q33);

        Question q34 = new Question("As compared to its parent alkane, an alkyl radical contains ",
                "One less hydrogen", "One less carbon", "One more carbon", 1,
                Question.DIFFICULTY_EASY, Category.CHEMISTRY);
        insertQuestion(q34);

        Question q35 = new Question("Rate of reaction of organic compounds is slow due to",
                "ionic bonding in them", "amphoteric nature", "covalent bonding", 3,
                Question.DIFFICULTY_EASY, Category.CHEMISTRY);
        insertQuestion(q35);

        Question q36 = new Question("Phenolphthalein in acidic solution is",
                "colorless", "pink colored", "yellow colored", 1,
                Question.DIFFICULTY_MEDIUM, Category.CHEMISTRY);
        insertQuestion(q36);

        Question q37 = new Question("Density of water becomes maximum at",
                "10°C", "4°C", "0°C", 2,
                Question.DIFFICULTY_MEDIUM, Category.CHEMISTRY);
        insertQuestion(q37);

        Question q38 = new Question("Who among the following had given the Atomic Theory?",
                "Madam Curie", "]Albert Einstein ", "John Dalton", 3,
                Question.DIFFICULTY_MEDIUM, Category.CHEMISTRY);
        insertQuestion(q38);

        Question q39 = new Question("Almost 95% of compounds are of carbon because it can form",
                "singe bond", "doble bonds", "mtliple bons", 3,
                Question.DIFFICULTY_MEDIUM, Category.CHEMISTRY);
        insertQuestion(q39);

        Question q40 = new Question("Water exist in only",
                "three states", "two states", "one state", 1,
                Question.DIFFICULTY_MEDIUM, Category.CHEMISTRY);
        insertQuestion(q40);

        Question q41 = new Question("The most electronegative element among the following is",
                "Bromine", "Fluorine", "Sodium", 2,
                Question.DIFFICULTY_HARD, Category.CHEMISTRY);
        insertQuestion(q41);

        Question q42 = new Question("Two different atoms A and B combine together to form AB. What will be AB?",
                "atom", "ion", "molecule", 3,
                Question.DIFFICULTY_HARD, Category.CHEMISTRY);
        insertQuestion(q42);


        Question q43 = new Question("Which among the following is a Noble Gas?",
                "Hydrogen ", "Helium", "Oxygen", 2,
                Question.DIFFICULTY_HARD, Category.CHEMISTRY);
        insertQuestion(q43);

        Question q44 = new Question("Which among the following element is an essential constituent of acids?",
                "Hydrogen", "Nitrogen", "Oxygen", 1,
                Question.DIFFICULTY_HARD, Category.CHEMISTRY);
        insertQuestion(q44);

        Question q45 = new Question("Among the given nutrients, milk is a poor source of",
                "Calcium ", "Vitamin C", "Protien", 2,
                Question.DIFFICULTY_HARD, Category.CHEMISTRY);
        insertQuestion(q45);
    }

    public void addQuestion(Question question) {
        db = getWritableDatabase();
        insertQuestion(question);
    }

    public void addQuestions(List<Question> questions) {
        db = getWritableDatabase();

        for (Question question : questions) {
            insertQuestion(question);
        }
    }

    private void insertQuestion(Question question) {
        ContentValues cv = new ContentValues();
        cv.put(QuestionsTable.COLUMN_QUESTION, question.getQuestion());
        cv.put(QuestionsTable.COLUMN_OPTION1, question.getOption1());
        cv.put(QuestionsTable.COLUMN_OPTION2, question.getOption2());
        cv.put(QuestionsTable.COLUMN_OPTION3, question.getOption3());
        cv.put(QuestionsTable.COLUMN_ANSWER_NR, question.getAnswerNr());
        cv.put(QuestionsTable.COLUMN_DIFFICULTY, question.getDifficulty());
        cv.put(QuestionsTable.COLUMN_CATEGORY_ID, question.getCategoryID());
        db.insert(QuestionsTable.TABLE_NAME, null, cv);
    }

    public List<Category> getAllCategories() {
        List<Category> categoryList = new ArrayList<>();
        db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + CategoriesTable.TABLE_NAME, null);

        if (c.moveToFirst()) {
            do {
                Category category = new Category();
                category.setId(c.getInt(c.getColumnIndex(CategoriesTable._ID)));
                category.setName(c.getString(c.getColumnIndex(CategoriesTable.COLUMN_NAME)));
                categoryList.add(category);
            } while (c.moveToNext());
        }

        c.close();
        return categoryList;
    }

    public ArrayList<Question> getAllQuestions() {
        ArrayList<Question> questionList = new ArrayList<>();
        db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + QuestionsTable.TABLE_NAME, null);

        if (c.moveToFirst()) {
            do {
                Question question = new Question();
                question.setId(c.getInt(c.getColumnIndex(QuestionsTable._ID)));
                question.setQuestion(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_QUESTION)));
                question.setOption1(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION1)));
                question.setOption2(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION2)));
                question.setOption3(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION3)));
                question.setAnswerNr(c.getInt(c.getColumnIndex(QuestionsTable.COLUMN_ANSWER_NR)));
                question.setDifficulty(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_DIFFICULTY)));
                question.setCategoryID(c.getInt(c.getColumnIndex(QuestionsTable.COLUMN_CATEGORY_ID)));
                questionList.add(question);
            } while (c.moveToNext());
        }

        c.close();
        return questionList;
    }

    public ArrayList<Question> getQuestions(int categoryID, String difficulty) {
        ArrayList<Question> questionList = new ArrayList<>();
        db = getReadableDatabase();

        String selection = QuestionsTable.COLUMN_CATEGORY_ID + " = ? " +
                " AND " + QuestionsTable.COLUMN_DIFFICULTY + " = ? ";
        String[] selectionArgs = new String[]{String.valueOf(categoryID), difficulty};

        Cursor c = db.query(
                QuestionsTable.TABLE_NAME,
                null,
                selection,
                selectionArgs,
                null,
                null,
                null
        );

        if (c.moveToFirst()) {
            do {
                Question question = new Question();
                question.setId(c.getInt(c.getColumnIndex(QuestionsTable._ID)));
                question.setQuestion(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_QUESTION)));
                question.setOption1(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION1)));
                question.setOption2(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION2)));
                question.setOption3(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION3)));
                question.setAnswerNr(c.getInt(c.getColumnIndex(QuestionsTable.COLUMN_ANSWER_NR)));
                question.setDifficulty(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_DIFFICULTY)));
                question.setCategoryID(c.getInt(c.getColumnIndex(QuestionsTable.COLUMN_CATEGORY_ID)));
                questionList.add(question);
            } while (c.moveToNext());
        }

        c.close();
        return questionList;
    }
}
