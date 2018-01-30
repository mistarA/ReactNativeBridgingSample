var IntentHelperNative = require('NativeModules').MyIntentHelper;

/**
 * IntentHelper gives an interface to make makePhoneCall, sendSms, launchGenericIntent and launchSettingIntent.
 * @constructor
 */

var MyIntentHelper = {

    /**
     * Show system settings.
     * @static
     */
    ACTION_SETTINGS: IntentHelperNative.ACTION_SETTINGS,

    /**
     * Show settings to allow configuration of wireless controls
     * such as Wi-Fi, Bluetooth and Mobile networks.
     * <p>
     * In some cases, a matching Activity may not exist, so ensure you
     * safeguard against this.
     * @static
     */
    ACTION_WIRELESS_SETTINGS: IntentHelperNative.ACTION_WIRELESS_SETTINGS,

    /**
     * Show settings to allow entering/exiting airplane mode.
     * <p>
     * In some cases, a matching Activity may not exist, so ensure you
     * safeguard against this.
     * @static
     */
    ACTION_AIRPLANE_MODE_SETTINGS: IntentHelperNative.ACTION_AIRPLANE_MODE_SETTINGS,

    /**
     *  Show settings to allow configuration of Wi-Fi.
     * <p>
     * In some cases, a matching Activity may not exist, so ensure you
     * safeguard against this.
     * @static
     */
    ACTION_WIFI_SETTINGS: IntentHelperNative.ACTION_WIFI_SETTINGS,

    /**
     *  Show settings to allow configuration of APNs.
     * @static
     */
    ACTION_APN_SETTINGS: IntentHelperNative.ACTION_APN_SETTINGS,

    /**
     * Show settings to allow configuration of Bluetooth.
     * <p>
     * In some cases, a matching Activity may not exist, so ensure you
     * safeguard against this.
     * @static
     */
    ACTION_BLUETOOTH_SETTINGS: IntentHelperNative.ACTION_BLUETOOTH_SETTINGS,

    /**
     * Show settings to allow configuration of date and time.
     * <p>
     * In some cases, a matching Activity may not exist, so ensure you
     * safeguard against this.
     * @static
     */
    ACTION_DATE_SETTINGS: IntentHelperNative.ACTION_DATE_SETTINGS,

    /**
     * Show settings to allow configuration of locale.
     * <p>
     * In some cases, a matching Activity may not exist, so ensure you
     * safeguard against this.
     * @static
     */
    ACTION_LOCALE_SETTINGS: IntentHelperNative.ACTION_LOCALE_SETTINGS,

    /**
     * Show settings to configure input methods, in particular
     * allowing the user to enable input methods.
     * <p>
     * In some cases, a matching Activity may not exist, so ensure you
     * safeguard against this.
     * @static
     */
    ACTION_INPUT_METHOD_SETTINGS: IntentHelperNative.ACTION_INPUT_METHOD_SETTINGS,

    /**
     * Show settings to allow configuration of display.
     * <p>
     * In some cases, a matching Activity may not exist, so ensure you
     * safeguard against this.
     * @static
     */
    ACTION_DISPLAY_SETTINGS: IntentHelperNative.ACTION_DISPLAY_SETTINGS,

    /**
     * Show settings to allow configuration of security and
     * location privacy.
     * <p>
     * In some cases, a matching Activity may not exist, so ensure you
     * safeguard against this.
     * @static
     */
    ACTION_SECURITY_SETTINGS: IntentHelperNative.ACTION_SECURITY_SETTINGS,

    /**
     * Show settings to allow configuration of current location
     * sources.
     * <p>
     * In some cases, a matching Activity may not exist, so ensure you
     * safeguard against this.
     * @static
     */
    ACTION_LOCATION_SOURCE_SETTINGS: IntentHelperNative.ACTION_LOCATION_SOURCE_SETTINGS,

    /**
     * Show settings for internal storage.
     * <p>
     * In some cases, a matching Activity may not exist, so ensure you
     * safeguard against this.
     * @static
     */
    ACTION_INTERNAL_STORAGE_SETTINGS: IntentHelperNative.ACTION_INTERNAL_STORAGE_SETTINGS,

    /**
     * Show settings for memory card storage.
     * <p>
     * In some cases, a matching Activity may not exist, so ensure you
     * safeguard against this.
     * @static
     */
    ACTION_MEMORY_CARD_SETTINGS: IntentHelperNative.ACTION_MEMORY_CARD_SETTINGS,

    /**
     * how screen of details about a particular application.
     * <p>
     * In some cases, a matching Activity may not exist, so ensure you
     * safeguard against this.
     * @static
     */
    ACTION_APPLICATION_DETAILS_SETTINGS: IntentHelperNative.ACTION_APPLICATION_DETAILS_SETTINGS,



    /**
     * Launches a call intent, requires "android.permission.CALL_PHONE" permission. For Api 21 in Android handle the runtime permission.
     * @param {string} phoneNumber
     * @returns {Promise}
     */
    makePhoneCall: function(phoneNumber) {
        return new Promise((resolve, reject) => {
            IntentHelperNative.makePhoneCall(phoneNumber).then((msg) => {
                resolve(msg);
            }).catch((err, msg) => {
                reject(err, msg);
            });
        });

    },

    /**
     * Launches a sms intent
     * @param {string} phoneNumber
     * @param {string} body
     * @returns {Promise}
     */
    sendSms: function(phoneNumber, body) {
        return new Promise((resolve, reject) => {
            IntentHelperNative.sendSms(phoneNumber, body).then((msg) => {
                resolve(msg);
            }).catch((err, msg) => {
                reject(err, msg);
            });
        });

    },

    /**
     * Launches a intent based on uri
     * @param {string} uri uri can be of type "geo:0,0?q=37.423156,-122.084917 (test)" etc
     * @returns {Promise<string>}
     */
    launchGenericIntent: function(uri) {

        return new Promise((resolve, reject) => {
            IntentHelperNative.launchGenericIntent(uri).then((msg) => {
                resolve(msg);
            }).catch((err, msg) => {
                reject(err, msg);
            });
        });
    },

    /**
         * Launches a settings intent
    * @param {string} intentName, must be of type string, can be one of the values of the constants IntentHelperNative.ACTION_SETTINGS, IntentHelperNative.ACTION_WIRELESS_SETTINGS, 
    IntentHelperNative.ACTION_AIRPLANE_MODE_SETTINGS, IntentHelperNative.ACTION_WIFI_SETTINGS, IntentHelperNative.ACTION_APN_SETTINGS, IntentHelperNative.ACTION_BLUETOOTH_SETTINGS, IntentHelperNative.ACTION_DATE_SETTINGS, 
    IntentHelperNative.ACTION_LOCALE_SETTINGS, IntentHelperNative.ACTION_INPUT_METHOD_SETTINGS, IntentHelperNative.ACTION_DISPLAY_SETTINGS, IntentHelperNative.ACTION_SECURITY_SETTINGS, IntentHelperNative.ACTION_LOCATION_SOURCE_SETTINGS,
    IntentHelperNative.ACTION_INTERNAL_STORAGE_SETTINGS, IntentHelperNative.ACTION_MEMORY_CARD_SETTINGS, IntentHelperNative.ACTION_APPLICATION_DETAILS_SETTINGS.
    By default the function will open ACTION_SETTINGS.
         */
    launchSettingIntent: function(intentName) {
        IntentHelperNative.launchSettingIntent(intentName);
    },
}

module.exports = MyIntentHelper;
