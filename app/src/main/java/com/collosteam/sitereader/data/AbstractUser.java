package com.collosteam.sitereader.data;

/**
 * Created by Collos on 9/1/2014.
 */
public abstract class AbstractUser implements User {

    protected String mName;
    protected String mPass;
    protected String mEmail;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;

        AbstractUser that = (AbstractUser) o;

        if (!mEmail.equals(that.mEmail)) return false;
        if (!mName.equals(that.mName)) return false;
        if (!mPass.equals(that.mPass)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = mName.hashCode();
        result = 31 * result + mPass.hashCode();
        result = 31 * result + mEmail.hashCode();
        return result;
    }
}
