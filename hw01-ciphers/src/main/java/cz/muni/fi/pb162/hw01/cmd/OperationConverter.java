package cz.muni.fi.pb162.hw01.cmd;

import com.beust.jcommander.converters.BaseConverter;
import cz.muni.fi.pb162.hw01.Operation;

/**
 * Converter for operation command line option
 */
public class OperationConverter extends BaseConverter<Operation> {

    /**
     * Constructor for this class
     * @param optionName name of the command line option
     */
    public OperationConverter(String optionName) {
        super(optionName);
    }

    @Override
    public Operation convert(String value) {
        return Operation.valueOf(value);
    }
}
