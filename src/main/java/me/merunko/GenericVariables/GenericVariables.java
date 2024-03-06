package me.merunko.GenericVariables;

public class GenericVariables {

    String CONFIG_FILE = "config.yml";
    String CONFIG_FILE_PATH = "plugins/Neopet/config.yml";
    String CONFIG_FILE_LOADED = "{65DB44}Loaded config.yml.";
    String CONFIG_FAILED_RELOAD = "{E03C3C}Failed to reload config.yml.";
    String CONFIG_SUCCESS_RELOAD = "{65DB44}Successfully reloaded config.yml.";
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

    public String getNoPermissionMsg() {
        return NO_PERMISSION;
    }

    public String invalidPlayer(String playerName) {
        return "{E03C3C}Player not found: " + playerName;
    }

}
