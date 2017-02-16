/**
 * 
 */
package gov.nasa.jpl.mudrod.xsd2owl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import tr.com.srdc.ontmalizer.XSD2OWLMapper;

/**
 * @author lewismc
 *
 */
public class Mapper {
    
    private static final Logger LOG = LoggerFactory.getLogger(Mapper.class);

    private static final String DIF_XSD = "dif_v10.2.xsd";

    private static InputStream is;
    
    private static final String INPUTFILE = "inputFile";
    /**
     * 
     */
    public Mapper() {
        // default constructor
    }

    public void executeMapping(InputStream is) {
        // This part converts XML schema to OWL ontology.
        XSD2OWLMapper mapping;
        if (is != null) {
            mapping = new XSD2OWLMapper(is);
        } else {
            mapping = new XSD2OWLMapper(getClass().getClassLoader().getResourceAsStream(DIF_XSD));
        }
        mapping.setObjectPropPrefix("");
        mapping.setDataTypePropPrefix("");
        mapping.convertXSD2OWL();

        // This part prints the ontology to the specified file.
        FileOutputStream ont;
        try {
            File f = new File("dif_v10.2.n3");
            f.getParentFile().mkdirs();
            ont = new FileOutputStream(f);
            mapping.writeOntology(ont, "N3");
            ont.close();
        } catch (Exception e) {
            LOG.error("Error writing converted XSD to N3 file: {}", e);
        }
    }
    /**
     * @param args
     */
    public static void main(String[] args) {
        Option sOpt = Option.builder().hasArg(true).numberOfArgs(1)
                .argName("file").required(true).longOpt(INPUTFILE)
                .desc("A path to a local XSD file.").build();

        Options opts = new Options();
        opts.addOption(sOpt);

        DefaultParser parser = new DefaultParser();
        CommandLine cmd = null;
        try {
            cmd = parser.parse(opts, args);
        } catch (ParseException e) {
            HelpFormatter formatter = new HelpFormatter();
            formatter.printHelp(Mapper.class.getSimpleName(), opts);
            System.exit(-1);
        }

        if (cmd.hasOption(INPUTFILE)) {
            try {
                is = new FileInputStream(cmd.getOptionValue(INPUTFILE));
            } catch (FileNotFoundException e) {
                LOG.error("Error processing input XSD from path: {}", e);
            }
        }
        Mapper mapper = new Mapper();
        mapper.executeMapping(is);
    }

}
