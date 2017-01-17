package plugin;

import org.elasticsearch.common.inject.Inject;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.plugins.Plugin;

public class MahasiwaPlugin extends Plugin {
	   private final Settings settings;
	
	   @Inject
	    public MahasiwaPlugin(Settings settings) {
	        this.settings = settings;
	    }


//	    public void onModule(ActionModule module) {
//	        if (settings.getAsBoolean("plugins.mahasiswa.enabled", true)) {
//	            module.registerAction(MahasiswaAction.INSTANCE, TransportMahasiswaAction.class);
//	        }
//	    }
//
//	    public void onModule(RestModule module) {
//	        if (settings.getAsBoolean("plugins.termlist.enabled", true)) {
//	            module.addRestAction(RestTermlistAction.class);
//	        }
//	    }
}
