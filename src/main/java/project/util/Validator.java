package project.util;

import project.dao.managerDao.DaoManager;

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
    private final String USER_PASSWORD_NOT_MORE_20_SIMBOLS = "^(.){0,20}$";

    private final String USER_NAMES_NOT_ALLOW_CHARACTERS = "^[a-zA-Zа-яА-я]{1,}$";
    private final String USER_NAMES_MORE_20 = "^(.){0,20}$";

    private final String TELEPHONE_NUMBER = "^(8|\\+7)\\d{10}$";
    private final String EMAIL = "^[-\\w.]+@([A-z0-9][-A-z0-9]+\\.)+[A-z]{2,4}$";
    private final String TIME24HOURS_PATTERN = "([01]?[0-9]|2[0-3]):[0-5][0-9]";

    private Map<String, String> results = new HashMap<>();
    private Map<String, String> invalidFields = new HashMap<>();
    private static Map<String, String> loginValidateFields = new HashMap<>();
    private static Map<String, String> passwordValidateFields = new HashMap<>();
    private Matcher matcher;
    private DaoManager daoManager;

    public Validator(DaoManager daoManager) {
        this.daoManager = daoManager;
    }

    public Validator() {
    }

    public Map<String, String> getInvalidFields() {
        return invalidFields;
    }

    public void initValidationFields() {
        loginValidateFields.put(USER_NAME_FIRST_CHARACTER_NOT_NUMBER, "login.first.is.number");
        loginValidateFields.put(USER_NAME_LANGTH_NOT_LESS_4_SIMBOLS, "login.less.4");
        loginValidateFields.put(USER_NAME_LANGTH_NOT_MORE_20_SIMBOLS, "login.more.then.20");
        loginValidateFields.put(USER_NAME_NOT_ALLOW_CHARACTERS, "login.illegal.characters");

        passwordValidateFields.put(USER_PASSWORD_NOT_ALLOW_CHARACTERS, "password.illegal.characters");
        passwordValidateFields.put(USER_PASSWORD_NOT_LESS_6_SIMBOLS, "password.less.6");
        passwordValidateFields.put(USER_PASSWORD_NUMBER_REQUIRED, "password.havent.got.number");
        passwordValidateFields.put(USER_PASSWORD_UPPERCASE_REQUIRED, "password.havent.got.uppercase");
        passwordValidateFields.put(USER_PASSWORD_LOWERCASE_REQUIRED, "password.havent.got.lowercase");
        passwordValidateFields.put(USER_PASSWORD_NOT_MORE_20_SIMBOLS, "password.more.then.20");
    }

    public boolean isValide() {
        for (Map.Entry<String, String> stringEntry : results.entrySet()) {
            if (stringEntry.getValue().equals("false")) {
                invalidFields.put(stringEntry.getKey().substring(0, stringEntry.getKey().indexOf(".")), stringEntry.getKey());
            }
        }
//* Returns true if this map contains no key-value mappings.
        if (invalidFields.isEmpty()) return true;
        else return false;
    }


    public void checkTime(String time) {
        if (time == null || time.equals("")) {
            results.put("time.is.required.field", "false");
        } else {
            matcher = Pattern.compile(TIME24HOURS_PATTERN).matcher(time);
            if (!matcher.matches())
                results.put("time enter not correct", "false");
        }
    }


    public void checkLogin(String login) {
        if (login == null || login.equals("")) {
            results.put("login.is.required.field", "false");
        } else {
            if (daoManager.getUserPostgresDao().alreadyExist(login)) {
                results.put("login already occupied", "false");
            } else {
                for (String key : loginValidateFields.keySet()) {
                    matcher = Pattern.compile(key).matcher(login);
                    if (!matcher.matches()) {
                        results.put(loginValidateFields.get(key), "false");
                    }
                }
            }
        }
    }

    public void checkUserPassword(String password) {
        if (password == null || password.equals("")) {
            results.put("password.is.required.field", "false");
        } else {
            for (String key : passwordValidateFields.keySet()) {
                matcher = Pattern.compile(key).matcher(password);
                if (!matcher.matches()) {
                    results.put(passwordValidateFields.get(key), "false");
                }
            }
        }
    }


    public void checkUserFirstName(String firstName) {
        if (firstName == null || firstName.equals("")) {
            results.put("firstName.is.required.field", "false");
        } else {
            matcher = Pattern.compile(USER_NAMES_NOT_ALLOW_CHARACTERS).matcher(firstName);
            if (!matcher.matches()) results.put("firstname.illegal.characters", "false");
            else {
                matcher = Pattern.compile(USER_NAMES_MORE_20).matcher(firstName);
                if (!matcher.matches()) results.put("firstname.more.then.20", "false");
            }
        }
    }

    public void checkUserLastName(String lastName) {
        if (lastName == null || lastName.equals("")) {
            results.put("lastName.is.required.field", "false");
        } else {
            matcher = Pattern.compile(USER_NAMES_NOT_ALLOW_CHARACTERS).matcher(lastName);
            if (!matcher.matches()) results.put("lastname.illegal.characters", "false");
            else {
                matcher = Pattern.compile(USER_NAMES_MORE_20).matcher(lastName);
                if (!matcher.matches()) results.put("lastname.more.then.20", "false");
            }
        }
    }


    public void checkUserPhone(String phone) {
        if (phone == null || phone.equals("")) {
            results.put("phone.is.required.field", "false");
        } else {
            matcher = Pattern.compile(TELEPHONE_NUMBER).matcher(phone);
            if (!matcher.matches()) results.put("telephone.is.incorrect", "false");
        }
    }

    public void checkEmail(String email) {
        if (email == null || email.equals("")) {
            results.put("email.is.required.field", "false");

        } else {
            matcher = Pattern.compile(EMAIL).matcher(email);
            if (!matcher.matches()) results.put("email.is.incorrect", "false");
        }

    }

    public void checkAddress(String address) {
        if (address == null || address.equals("")) {
            results.put("address.is.required.field", "false");
        } else {
            matcher = Pattern.compile(USER_NAMES_MORE_20).matcher(address);
            if (!matcher.matches()) results.put("address.more.then.20", "false");
        }
    }
}
