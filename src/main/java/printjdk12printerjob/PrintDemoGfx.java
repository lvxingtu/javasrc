import java.awt.*;
import java.awt.print.*;

/** PrintDemoGfx -- Construct and print a GfxDemoCanvas. JDK1.2 VERSION. */
public class PrintDemoGfx {

	/** Simple demo main program. */
	public static void main(String av[]) throws PrinterException {
		PrinterJob pjob = PrinterJob.getPrinterJob();
		pjob.setJobName("Printing Test");

		// Construct the object we want to print. Contrived:
		// this object would already exist in a real program.
		final GfxDemoCanvas thing = new GfxDemoCanvas(400, 300);

		// Tell the print system how to print our pages.
		pjob.setPrintable(new Printable() {
			/** called from the printer system to print each page */
			public int print(Graphics pg, PageFormat pf, int pageNum) {
				if (pageNum>0)		// we only print one page
					return Printable.NO_SUCH_PAGE;	// ie., end of job

				// Now (drum roll please), ask "thing" to paint itself
				// on the printer, by calling its paint() method with 
				// a Printjob Graphics instead of a Window Graphics.
				thing.paint(pg);

				// Tell print system that the page is ready to print
				return Printable.PAGE_EXISTS;
			}
		});

		if (pjob.printDialog() == false)	// choose printer
			return;				// user cancelled

		pjob.print();			 // Finally, do the printing.
	}
}
