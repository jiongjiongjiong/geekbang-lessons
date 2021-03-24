package config.converter;


/**
 * @author: zhangc
 * @date: 2021/3/24
 * @desc:
 */
public class StringConverter extends AbstractConverter<String> {
    @Override
    protected String doConvert(String value) {
        return value;
    }
}
