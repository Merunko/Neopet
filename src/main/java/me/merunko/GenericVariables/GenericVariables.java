package me.merunko.GenericVariables;

public class GenericVariables {

    String CONFIG_FILE = "config.yml";
    String CONFIG_FILE_PATH = "plugins/Neopet/config.yml";
    String CONFIG_FILE_LOADED = "{65DB44}Loaded config.yml.";
    String CONFIG_FAILED_RELOAD = "{E03C3C}Failed to reload config.yml.";
    String CONFIG_SUCCESS_RELOAD = "{65DB44}Successfully reloaded config.yml.";

    String INSTANCE_SETTING_FILE_PATH = "plugins/Neopet/instancesSetting";

    String NO_SUBMIT_PERMISSION = "{E03C3C}You don't have the permission to submit!";
    String NO_PERMISSION = "{E03C3C}You don't have permission to run that command!";

    public String getConfigFileName() {
        return CONFIG_FILE;
    }

    public String getMainConfigPath() {
        return CONFIG_FILE_PATH;
    }

    public String getMainConfigLoadedMsg() {
        return CONFIG_FILE_LOADED;
    }

    public String getMainConfigReloadFailedMsg() {
        return CONFIG_FAILED_RELOAD;
    }

    public String getMainConfigReloadSuccessMsg() {
        return CONFIG_SUCCESS_RELOAD;
    }

    public String getInstanceConfigPath() {
        return INSTANCE_SETTING_FILE_PATH;
    }

    public String noInstanceSettingFound(String fileName) {
        return "{E03C3C}No instance setting found: &6" + fileName + ".yml";
    }

    public String noSubmitPermissionMsg() {return NO_SUBMIT_PERMISSION; }

    public String getNoPermissionMsg() {
        return NO_PERMISSION;
    }

    public String invalidPlayer(String playerName) {
        return "{E03C3C}Player not found: &6" + playerName;
    }

    public String invalidMaterialID(String materialID) {
        return "{E03C3C}Invalid material ID: &6" + materialID;
    }

    public String invalidCustomModelData(String modelNumber) {
        return "{E03C3C}Invalid custom model data: &6" + modelNumber;
    }

    public String negativeCustomModelData(int modelNumber) {
        return "{E03C3C}Negative custom model data: &6" + modelNumber;
    }

}
