package controllers;

import models.Member;
import play.Logger;
import play.mvc.Controller;

public class Accounts extends Controller
{
    public static void index()
    {
        Member member = getLoggedInMember();
        render("accounts.html", member);
    }

    public static void signup()
    {
        render("signup.html");
    }

    public static void login()
    {
        render("login.html");
    }

    public static void register(String firstname, String lastname, String email, String password)
    {
        Logger.info("Registering new user " + email);
        Member member = new Member(firstname, lastname, email, password);
        try
        {
            member.save();
        }
        catch (Exception e)
        {
            redirect("login");
        }
        redirect("/");
    }

    public static void authenticate(String email, String password)
    {
        Logger.info("Attempting to authenticate with " + email + ":" + password);

        Member member = Member.findByEmail(email);
        if ((member != null) && (member.checkPassword(password) == true)) {
            Logger.info("Authentication successful");
            session.put("logged_in_Memberid", member.id);
            redirect ("/dashboard");
        } else {
            Logger.info("Authentication failed");
            redirect("/login");
        }
    }

    public static Member getLoggedInMember()
    {
        Member member = null;
        if (session.contains("logged_in_Memberid")) {
            String memberId = session.get("logged_in_Memberid");
            member = Member.findById(Long.parseLong(memberId));
        } else {
            login();
        }
        return member;
    }

    /**
     * This method is used to update the users details
     *
     * @param firstname This is the first name of the person
     * @param lastname  This is the last name of the person
     * @param email     This is the persons email address
     * @param password  This is the persons password
     */
    public static void update(Long id, String firstname, String lastname, String email, String password)
    {
     Member member = Member.findById(id);
     member.firstname = firstname;
     member.lastname = lastname;
     member.email = email;
     member.password = password;
     member.save();
     redirect("/dashboard");
    }
}