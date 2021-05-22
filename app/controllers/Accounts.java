package controllers;

import models.Member;
import play.Logger;
import play.mvc.Controller;

/**
 * Accounts controller handles the user accounts
 * for the WeatherTop application
 *
 * @author Patrick Marnane
 * @version 1.0
 * @since 2021-05-17
 */
public class Accounts extends Controller {

    public static void index() {
        Member member = getLoggedInMember();
        render("accounts.html", member);
    }

    public static void signup() {
        render("signup.html");
    }

    public static void login() {
        render("login.html");
    }

    public static void register(String firstname, String lastname, String email, String password) {
        Member member = Member.findByEmail(email);
        if (member == null) {
            Logger.info("Registering new user " + email);
            member = new Member(firstname, lastname, email, password);
            try {
                member.save();
            } catch (Exception e) {
                redirect("signup");
            }
            redirect("/login");
        } else {
            String message = "Email address has an existing account, please log in.";
            render("login.html", message);
        }
    }

    public static void authenticate(String email, String password) {
        Logger.info("Attempting to authenticate with " + email + ":" + password);

        Member member = Member.findByEmail(email);
        if ((member != null) && (member.checkPassword(password) == true)) {
            Logger.info("Authentication successful");
            session.put("logged_in_Memberid", member.id);
            redirect("/dashboard");
        } else {
            String message = "Incorrect email/password, please try again.";
            render("login.html", message);;
        }
    }

    public static Member getLoggedInMember() {
        Member member = null;
        if (session.contains("logged_in_Memberid")) {
            String memberId = session.get("logged_in_Memberid");
            member = Member.findById(Long.parseLong(memberId));
        } else {
            login();
        }
        return member;
    }

    public static void update(Long id, String firstname, String lastname, String email, String password) {
        Member member = Member.findById(id);
        member.setFirstname(firstname);
        member.setLastname(lastname);
        member.setEmail(email);
        member.setPassword(password);
        member.save();
        redirect("/dashboard");
    }

    public static void delete(Long id) {
        Member member = Member.findById(id);
        member.delete();
        redirect("/");
    }
}