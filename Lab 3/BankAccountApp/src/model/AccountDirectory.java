package model;

import java.util.ArrayList;

public class AccountDirectory {
    private ArrayList<Account> accounts = new ArrayList<>();

    public ArrayList<Account> getAccounts() { return accounts; }

    public Account addAccount() {
        Account a = new Account();
        accounts.add(a);
        return a;
    }

    public void deleteAccount(Account a) { accounts.remove(a); }

    public Account searchAccount(String accountNumber) {
        for (Account a : accounts) {
            if (a.getAccountNumber().contains(accountNumber)) {
                return a;
            }
        }
        return null;
    }
}