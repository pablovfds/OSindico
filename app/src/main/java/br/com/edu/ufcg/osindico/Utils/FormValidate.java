package br.com.edu.ufcg.osindico.Utils;

import android.text.TextUtils;
import android.util.Patterns;

/**
 * Created by emanoel on 20/02/17.
 */

public class FormValidate {

    public static boolean isNomeValido(CharSequence nome) {
        return !TextUtils.isEmpty(nome);
    }

    public static boolean isEmailValido(CharSequence email) {
        return !TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    public static boolean isSenhaValida(CharSequence senha) {
        return !TextUtils.isEmpty(senha) && senha.length() >= 8;
    }

    public static boolean isConfirmarSenhaValida(CharSequence senha, CharSequence confirmarSenha) {
        return senha.equals(confirmarSenha);
    }

    public static boolean isTelefoneValido(CharSequence telefone) {
        return !TextUtils.isEmpty(telefone) && Patterns.PHONE.matcher(telefone).matches();
    }
}
