'use strict';
import  PropTypes from 'prop-types'
import { NativeModules, requireNativeComponent, View} from 'react-native'

var iface = {
    name: 'ProgressBar',
    propTypes: {
        progress: PropTypes.number,
        indeterminate: PropTypes.bool,
        ...View.propTypes 
    },
};

var ProgressBar = requireNativeComponent('ProgressBar', iface);

export default ProgressBar;