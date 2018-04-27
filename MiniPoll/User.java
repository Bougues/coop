package be.groupe7lsinf1225.minipoll.Object;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.SparseArray;
import java.util.ArrayList;

import be.groupe7lsinf1225.minipoll.MySQLiteHelper;
/**
 * Created by Nicolas on 26/04/2018.
 */

public class User {


    private static final String DB_COLUMN_id = "id";
    private static final String DB_COLUMN_nom = "nom";
    private static final String DB_COLUMN_prenom = "prenom";
    private static final String DB_COLUMN_login = "login";
    private static final String DB_COLUMN_password = "password";
    private static final String DB_COLUMN_image = "image";
    private static final String DB_COLUMN_bestfriend = "bestfriend";
    private static final String DB_COLUMN_amis = "amis";
    private static final String DB_COLUMN_email = "email";
    private static final String DB_TABLE = "users";

    /**
     * Contient les instances déjà existantes des utilisateurs afin d'éviter de créer deux instances
     * du même utilisateur.
     */
    private static SparseArray<User> userSparseArray = new SparseArray<>();

    /**
     * Utilisateur actuellement connecté à l'application. Correspond à null si aucun utilisateur
     * n'est connecté.
     */
    private static User connectedUser = null;

    /**
     * Identifiant unique de l'utilisateur courant. Correspond à _id dans la base de données.
     */
    private final int id;

    /**
     * Login (unique) de l'utilisateur courant. Correspond à login dans la base de données.
     */
    private String login;

    /**
     * Mot de passe de l'utilisateur courant. Correspond à u_password dans la base de données.
     */
    private String password;

    /**
     * Constructeur de l'utilisateur. Initialise une instance de l'utilisateur présent dans la base
     * de données.
     *
     * @note Ce constructeur est privé (donc utilisable uniquement depuis cette classe). Cela permet
     * d'éviter d'avoir deux instances différentes d'un même utilisateur.
     */
    private User(int id, String nom, String password) {

        this.id = id;
        this.login = login;
        this.password = password;
        User.userSparseArray.put(id, this);
    }

    /**
     * Fournit l'utilisateur actuellement connecté.
     */
    public static User getConnectedUser() {
        return User.connectedUser;
    }

    /**
     * Déconnecte l'utilisateur actuellement connecté à l'application.
     */
    public static void logout() {
        User.connectedUser = null;
        //MiniPoll.stop();
    }

    /**
     * Fournit l'id de l'utilisateur courant.
     */
    public int getId() {
        return id;
    }

    /**
     * Fournit le nom de l'utilisateur courant.
     */
    public String getLogin() {
        return login;
    }

    /**
     * Connecte l'utilisateur courant.
     *
     * @param passwordToTry le mot de passe entré.
     *
     * @return Vrai (true) si l'utilisateur à l'autorisation de se connecter, false sinon.
     */
    public boolean login(String passwordToTry) {
        if (this.password.equals(passwordToTry)) {
            // Si le mot de passe est correct, modification de l'utilisateur connecté.
            User.connectedUser = this;
            return true;
        }
        return false;
    }

    /**
     * Fournit une représentation textuelle de l'utilisateur courant. (Ici le nom)
     *
     * @note Cette méthode est utilisée par l'adaptateur ArrayAdapter afin d'afficher la liste des
     * utilisateurs. (Voir LoginActivity).
     */
    public String toString() {
        return getLogin();
    }

    /**
     * Fournit la liste des utilisateurs.
     */
    public static ArrayList<User> getUtilisateurs() {
        // Récupération du  SQLiteHelper et de la base de données.
        SQLiteDatabase db = MySQLiteHelper.get().getReadableDatabase();

        // Colonnes à récupérer
        String[] colonnes = {DB_COLUMN_id, DB_COLUMN_login, DB_COLUMN_password};

        // Requête de selection (SELECT)
        Cursor cursor = db.query(DB_TABLE, colonnes, null, null, null, null, null);

        // Placement du curseur sur la première ligne.
        cursor.moveToFirst();

        // Initialisation la liste des utilisateurs.
        ArrayList<User> users = new ArrayList<>();

        // Tant qu'il y a des lignes.
        while (!cursor.isAfterLast()) {
            // Récupération des informations de l'utilisateur pour chaque ligne.
            int uId = cursor.getInt(0);
            String uNom = cursor.getString(1);
            String uPassword = cursor.getString(2);

            // Vérification pour savoir s'il y a déjà une instance de cet utilisateur.
            User user = User.userSparseArray.get(uId);
            if (user == null) {
                // Si pas encore d'instance, création d'une nouvelle instance.
                user = new User(uId, uNom, uPassword);
            }

            // Ajout de l'utilisateur à la liste.
            users.add(user);

            // Passe à la ligne suivante.
            cursor.moveToNext();
        }

        // Fermeture du curseur et de la base de données.
        cursor.close();
        db.close();

        return users;
    }
}
