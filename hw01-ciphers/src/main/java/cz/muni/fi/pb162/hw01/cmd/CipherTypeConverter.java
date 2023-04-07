package cz.muni.fi.pb162.hw01.cmd;

import com.beust.jcommander.converters.BaseConverter;
import cz.muni.fi.pb162.hw01.CipherType;

/**
 * Converter for cipher type command line option
 */
public class CipherTypeConverter extends BaseConverter<CipherType> {

    /**
     * Constructor for this class
     * @param optionName name of the command line option
     */
    public CipherTypeConverter(String optionName) {
        super(optionName);
    }

    @Override
    public CipherType convert(String value) {
       return CipherType.valueOf(value);
    }
}
