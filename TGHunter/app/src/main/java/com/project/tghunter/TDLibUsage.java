package com.project.tghunter;

import android.content.Context;

import org.drinkless.td.libcore.telegram.Client;
import org.drinkless.td.libcore.telegram.TdApi;

public class TDLibUsage extends Thread {
    private static Context context;

    private static Client client;

    private static String phoneNumber;
    public static String verificationCode;

    public TDLibUsage(String phoneNumber, Context context, Client client) {
        this.phoneNumber = phoneNumber;
        this.context = context;
        this.client = client;
    }

    @Override
    public void run() {
        FileEditor.writeLog(context, "[TDLib Thread] starting TDLibUsage thread");

        client.run();
    }

    static class UpdateHandler implements Client.ResultHandler {
        @Override
        public void onResult(TdApi.Object object) {
            switch (object.getConstructor()) {
                case TdApi.UpdateAuthorizationState.CONSTRUCTOR:
                    onAuthorizationStateUpdated(((TdApi.UpdateAuthorizationState) object).authorizationState, client, context);
                    FileEditor.writeLog(context, "[TDLib Thread] updateHandler: onResult() method is activated, case TdApi.UpdateAuthorizationState.CONSTRUCTOR working: " + object.getConstructor());
            }
        }
    }

    private static void onAuthorizationStateUpdated(TdApi.AuthorizationState authorizationState, Client client, Context context) {
        switch (authorizationState.getConstructor()) {
            case TdApi.AuthorizationStateWaitTdlibParameters.CONSTRUCTOR:
                TdApi.TdlibParameters tdlibParameters = new TdApi.TdlibParameters();

                tdlibParameters.useTestDc = false;
                tdlibParameters.databaseDirectory = context.getApplicationContext().getFilesDir().getAbsolutePath();
                tdlibParameters.filesDirectory = context.getApplicationContext().getFilesDir().toString();
                tdlibParameters.useFileDatabase = false;
                tdlibParameters.useChatInfoDatabase = false;
                tdlibParameters.useMessageDatabase = false;
                tdlibParameters.useSecretChats = true;
//                tdlibParameters.apiId = 94575;
//                tdlibParameters.apiHash = "a3406de8d171bb422bb6ddf3bbd800e2";
                tdlibParameters.systemLanguageCode = "en";
                tdlibParameters.deviceModel = "Galaxy S21";
                tdlibParameters.systemVersion = "Android 13";
                tdlibParameters.applicationVersion = "1.0";
                tdlibParameters.enableStorageOptimizer = true;
                tdlibParameters.ignoreFileNames = false;

                TdApi.SetTdlibParameters request = new TdApi.SetTdlibParameters(tdlibParameters);

                client.send(request, null);
                FileEditor.writeLog(context, "[TDLib Thread] case TdApi.AuthorizationStateWaitTdlibParameters.CONSTRUCTOR activated, sending tdlib parameters");
                break;
            case TdApi.AuthorizationStateWaitPhoneNumber.CONSTRUCTOR:
                if (phoneNumber != null || phoneNumber != "") {
                    client.send(new TdApi.SetAuthenticationPhoneNumber(phoneNumber, null), null);
                    FileEditor.writeLog(context, "[TDLib Thread] case TdApi.AuthorizationStateWaitPhoneNumber.CONSTRUCTOR activated, sending phone number: " + phoneNumber);
                } else {
                    FileEditor.writeLog(context, "[TDLib Thread] case TdApi.AuthorizationStateWaitPhoneNumber.CONSTRUCTOR activated, but phone number is invalid");
                }
                break;
            case TdApi.AuthorizationStateWaitEncryptionKey.CONSTRUCTOR:
                client.send(new TdApi.SetDatabaseEncryptionKey(new byte[256]), null);
                FileEditor.writeLog(context, "[TDLib Thread] setting new encryption key: caused by TdApi.AuthorizationStateWaitEncryptionKey");
                break;
            case TdApi.AuthorizationStateWaitOtherDeviceConfirmation.CONSTRUCTOR:
//                String link = ((TdApi.AuthorizationStateWaitOtherDeviceConfirmation) Example.authorizationState).link;
//                System.out.println("Please confirm this login link on another device: " + link);
                break;
            case TdApi.AuthorizationStateWaitCode.CONSTRUCTOR:
                if (verificationCode != null || verificationCode != "") {
                    client.send(new TdApi.CheckAuthenticationCode(verificationCode), null);
                    FileEditor.writeLog(context, "[TDLib Thread] case TdApi.AuthorizationStateWaitCode.CONSTRUCTOR activated, sending verification code");
                } else {
                    FileEditor.writeLog(context, "[TDLib Thread] case TdApi.AuthorizationStateWaitCode.CONSTRUCTOR activated, but verification code contains wrong value");
                }
                break;
            case TdApi.AuthorizationStateWaitRegistration.CONSTRUCTOR:
                if (VerifyNumberActivity.queryMessage != null || VerifyNumberActivity.queryMessage != "") {
                    String[] valuesForRegistration = VerifyNumberActivity.queryMessage.split(" ");
                    client.send(new TdApi.RegisterUser(valuesForRegistration[0], valuesForRegistration[1]), null);
                    FileEditor.writeLog(context, "[TDLib Thread] case TdApi.AuthorizationStateWaitRegistration.CONSTRUCTOR activated, sending request for registration");
                } else {
                    FileEditor.writeLog(context, "[TDLib Thread] case TdApi.AuthorizationStateWaitRegistration.CONSTRUCTOR activated, sending phone number");
                }
                break;
            case TdApi.AuthorizationStateWaitPassword.CONSTRUCTOR:
                if (VerifyNumberActivity.queryMessage != null || VerifyNumberActivity.queryMessage != "") {
                    client.send(new TdApi.CheckAuthenticationPassword(VerifyNumberActivity.queryMessage), null);
                    FileEditor.writeLog(context, "[TDLib Thread] case TdApi.AuthorizationStateWaitPassword.CONSTRUCTOR activated, sending password to login");
                } else {
                    FileEditor.writeLog(context, "[TDLib Thread] case TdApi.AuthorizationStateWaitPassword.CONSTRUCTOR activated, but queryMessage contains wrong message");
                }
                break;
            case TdApi.AuthorizationStateReady.CONSTRUCTOR:
//                haveAuthorization = true;
//                authorizationLock.lock();
//                try {
//                    gotAuthorization.signal();
//                } finally {
//                    authorizationLock.unlock();
//                }
//                break;
            default:
                FileEditor.writeLog(context, "[TDLib Thread] unsupported authorizationState: " + authorizationState);
        }
    }
}
