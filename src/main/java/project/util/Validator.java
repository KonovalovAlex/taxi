package project.util;

import project.dao.managerDao.ManagerDao;
import project.entity.Client;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {

    private final String USER_NAME_FIRST_CHARACTER_NOT_NUMBER = "^[a-zA-Z][a-zA-Z0-9-_\\.]{1,}$";
    private final String USER_NAME_LANGTH_NOT_MORE_20_SIMBOLS = "(.){0,20}$";
    private final String USER_NAME_LANGTH_NOT_LESS_4_SIMBOLS = "^(.){4,}";
    private final String USER_NAME_NOT_ALLOW_CHARACTERS = "^[a-zA-Z0-9-_\\.]{0,}$";

    private final String USER_PASSWORD_NUMBER_REQUIRED = "^(?=.*\\d).*$";
    private final String USER_PASSWORD_UPPERCASE_REQUIRED = "^(?=.*[A-Z]).*$";
    private final String USER_PASSWORD_LOWERCASE_REQUIRED = "^(?=.*[a-z]).*$";
    private final String USER_PASSWORD_NOT_ALLOW_CHARACTERS = "^[a-zA-Z0-9]{0,}$";
    private final String USER_PASSWORD_NOT_LESS_6_SIMBOLS = "^(.){6,}$";
    //    private final String USER_PASSWORD_NOT_MORE_20_SIMBOLS = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?!.*\\s).*$";
    private final String USER_PASSWORD_NOT_MORE_20_SIMBOLS = "^(.){0,20}$";
    private final String USER_NAMES_NOT_ALLOW_CHARACTERS = "^[a-zA-Zа-яА-я]{1,}$";
    private final String USER_NAMES_MORE_20 = "^(.){0,20}$";

    private final String ADDRESS_LANGTH_NOT_MORE_20_SIMBOLS = "(.){0,20}$";
    private final String ADDRESS_NOT_ALLOW_CHARACTERS = "[a-zA-Z0-9а-яА-я-\\s\\/]{0,}$";
    private final String ADDRESS = "^[a-zA-Z][a-zA-Z0-9-_\\.\\ ]{1,19}$";
    private final String DATE = "(0[1-9]|[12][0-9]|3[01])[- /.](0[1-9]|1[012])[- /.](19|20)\\d\\d";
    private final String TIME = "^([0-1]\\d|2[0-3])(:[0-5]\\d){2}$";
    private final String TELEPHONE_NUMBER = "^(8|\\+7)\\d{10}$";
    private final String EMAIL = "^[-\\w.]+@([A-z0-9][-A-z0-9]+\\.)+[A-z]{2,4}$";

    private Map<String, String> results = new HashMap<>();
    private Map<String, String> invalidFields = new HashMap<>();

    private Matcher matcher;
    private ManagerDao daoManager;

    public Validator(ManagerDao daoManager) {
        this.daoManager = daoManager;
    }

    public Validator() {

    }

    public boolean isValide() {

        for (Map.Entry<String, String> stringEntry : results.entrySet()) {
            if (stringEntry.getValue().equals("false")) {
                invalidFields.put(stringEntry.getKey().substring(0, stringEntry.getKey().indexOf(".")), stringEntry.getKey());
            }
            if (invalidFields.isEmpty()) return true;
            else return false;
        }
//* Returns <tt>true</tt> if this map contains no key-value mappings.
        if (invalidFields.isEmpty()) return true;
        else return false;
    }

    public Map<String, String> getInvalidFields() {
        return invalidFields;
    }

    public boolean checkUserName(String name) {

        if (name == null || name.equals("")) {
            results.put("login.is.required.field", "false");
            return false;
        } else {
            if (daoManager.getClientPostgresDao().alreadyExist(name)) {
                results.put("login.already.occupied", "false");
                return false;
            } else {
                matcher = Pattern.compile(USER_NAME_NOT_ALLOW_CHARACTERS).matcher(name);
                if (!matcher.matches()) results.put("login.illegal.characters", String.valueOf(matcher.matches()));
                else {
                    matcher = Pattern.compile(USER_NAME_LANGTH_NOT_LESS_4_SIMBOLS).matcher(name);
                    if (!matcher.matches()) results.put("login.less.4", String.valueOf(matcher.matches()));
                    else {
                        matcher = Pattern.compile(USER_NAME_FIRST_CHARACTER_NOT_NUMBER).matcher(name);
                        if (!matcher.matches())
                            results.put("login.first.is.number", String.valueOf(matcher.matches()));
                        else {
                            matcher = Pattern.compile(USER_NAME_LANGTH_NOT_MORE_20_SIMBOLS).matcher(name);
                            if (!matcher.matches())
                                results.put("login.more.then.20", String.valueOf(matcher.matches()));
                        }
                    }
                }
                return matcher.matches();
            }
        }
    }

    public boolean checkUserPassword(String password) {

        if (password == null || password.equals("")) {
            results.put("password.is.required.field", "false");
            return false;
        } else {
            matcher = Pattern.compile(USER_PASSWORD_NOT_ALLOW_CHARACTERS).matcher(password);
            if (!matcher.matches()) results.put("password.illegal.characters", String.valueOf(matcher.matches()));
            else {
                matcher = Pattern.compile(USER_PASSWORD_NOT_LESS_6_SIMBOLS).matcher(password);
                if (!matcher.matches()) results.put("password.less.6", String.valueOf(matcher.matches()));
                else {
                    matcher = Pattern.compile(USER_PASSWORD_NUMBER_REQUIRED).matcher(password);
                    if (!matcher.matches())
                        results.put("password.havent.got.number", String.valueOf(matcher.matches()));
                    else {
                        matcher = Pattern.compile(USER_PASSWORD_UPPERCASE_REQUIRED).matcher(password);
                        if (!matcher.matches())
                            results.put("password.havent.got.uppercase", String.valueOf(matcher.matches()));
                        else {
                            matcher = Pattern.compile(USER_PASSWORD_LOWERCASE_REQUIRED).matcher(password);
                            if (!matcher.matches())
                                results.put("password.havent.got.lowercase", String.valueOf(matcher.matches()));
                            else {
                                matcher = Pattern.compile(USER_PASSWORD_NOT_MORE_20_SIMBOLS).matcher(password);
                                if (!matcher.matches())
                                    results.put("password.more.then.20", String.valueOf(matcher.matches()));
                            }
                        }
                    }
                }
            }
            return matcher.matches();
        }
    }

    public boolean checkOldPassword(String password, Client user) {
        if (!password.equals(user.getPassword())) {
            results.put("oldpassword.false", "false");
        }
        return password.equals(user.getPassword());
    }

    public boolean checkConfirmUserPassword(String password, String confirm) {
        if (!password.equals(confirm)) {
            results.put("confirmpassword.false", "false");
        }
        return password.equals(confirm);
    }

    public boolean checkUserFirstName(String firstname) {
        if (!firstname.equals("")) {
            matcher = Pattern.compile(USER_NAMES_NOT_ALLOW_CHARACTERS).matcher(firstname);
            if (!matcher.matches()) results.put("firstname.illegal.characters", String.valueOf(matcher.matches()));
            else {
                matcher = Pattern.compile(USER_NAMES_MORE_20).matcher(firstname);
                if (!matcher.matches()) results.put("firstname.more.then.20", String.valueOf(matcher.matches()));
            }
            return matcher.matches();
        }
        return true;
    }

    public boolean checkUserLastName(String lastname) {
        if (!lastname.equals("")) {
            matcher = Pattern.compile(USER_NAMES_NOT_ALLOW_CHARACTERS).matcher(lastname);
            if (!matcher.matches()) results.put("lastname.illegal.characters", String.valueOf(matcher.matches()));
            else {
                matcher = Pattern.compile(USER_NAMES_MORE_20).matcher(lastname);
                if (!matcher.matches()) results.put("lastname.more.then.20", String.valueOf(matcher.matches()));
            }
            return matcher.matches();
        }
        return true;
    }


    public boolean checkUserPhone(String phone) {
        if (!phone.equals("")) {
            matcher = Pattern.compile(TELEPHONE_NUMBER).matcher(phone);
            if (!matcher.matches()) results.put("telephone.is.incorrect", String.valueOf(matcher.matches()));
            return matcher.matches();
        }
        return true;
    }
    public boolean checkEmail(String email) {
        if (email == null || email.equals("")) {
            results.put("email.is.required.field", "false");
            return false;
        } else {
            matcher = Pattern.compile(EMAIL).matcher(email);
            if (!matcher.matches()) results.put("email.is.incorrect", String.valueOf(matcher.matches()));
        }
        return matcher.matches();
    }
}
