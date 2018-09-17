package raymond.report;
import java.util.HashMap;
import java.util.logging.Level;
 
import org.eclipse.birt.core.framework.Platform;
import org.eclipse.birt.report.engine.api.EngineConfig;
import org.eclipse.birt.report.engine.api.EngineConstants;
import org.eclipse.birt.report.engine.api.EngineException;
import org.eclipse.birt.report.engine.api.HTMLActionHandler;
import org.eclipse.birt.report.engine.api.HTMLEmitterConfig;
import org.eclipse.birt.report.engine.api.HTMLRenderContext;
import org.eclipse.birt.report.engine.api.HTMLRenderOption;
import org.eclipse.birt.report.engine.api.HTMLServerImageHandler;
import org.eclipse.birt.report.engine.api.IReportEngine;
import org.eclipse.birt.report.engine.api.IReportEngineFactory;
import org.eclipse.birt.report.engine.api.IReportRunnable;
import org.eclipse.birt.report.engine.api.IRunAndRenderTask;
import org.eclipse.birt.report.engine.api.PDFRenderOption;

public class ExecuteReport {
	/**
	 * @throws EngineException
	 */
	public static void executeReport() throws EngineException {
		 
	    IReportEngine engine = null;
	    EngineConfig config = null;
	 
	    try {
	        config = new EngineConfig();  
	        config.setLogConfig("c:/birt4.8/log", Level.FINEST);
	        Platform.startup(config);
	        final IReportEngineFactory FACTORY = (IReportEngineFactory) Platform
	            .createFactoryObject(IReportEngineFactory.EXTENSION_REPORT_ENGINE_FACTORY);
	        engine = FACTORY.createReportEngine(config);       
	 
	        // Open the report design
	        IReportRunnable design = null;
	        design = engine.openReportDesign("C:/birt4.8/ReportEngine/samples/nr3.rptdesign"); 
	        IRunAndRenderTask task = engine.createRunAndRenderTask(design);  
	        String s = "Individual to Pay All Charges";
	        task.setParameterValue("desc", s);
	        task.validateParameters();
	 
//	        final HTMLRenderOption HTML_OPTIONS = new HTMLRenderOption();       
//	        HTML_OPTIONS.setOutputFileName("C:/birt4.8/ReportEngine/resamples/hello_world.html");
//	        HTML_OPTIONS.setOutputFormat("html");
	        // HTML_OPTIONS.setHtmlRtLFlag(false);
	        // HTML_OPTIONS.setEmbeddable(false);
	        // HTML_OPTIONS.setImageDirectory("C:\\test\\images");
	 
	         final PDFRenderOption PDF_OPTIONS = new PDFRenderOption();
	         PDF_OPTIONS.setOutputFileName("C:/birt4.8/ReportEngine/resamples/nr3.pdf");
	         PDF_OPTIONS.setOutputFormat("pdf");
	 
	        task.setRenderOption(PDF_OPTIONS);
	        task.run();
	        task.close();
	        engine.destroy();
	    } catch(final Exception EX) {
	        EX.printStackTrace();
	    } finally {
	       Platform.shutdown();
	    }
	}

    public static void main() {
        try {
           executeReport();
        } catch (final Exception EX) {
           EX.printStackTrace();
        }
    }
}