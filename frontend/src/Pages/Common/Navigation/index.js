import 'Assets/icon'
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'

export default function Navigation() {

    return (
        <div>
            <nav className='menu'>
            <input type={"checkbox"} className="menu-open" name="menu-open" id="menu-open" />
            <label className='menu-open-button' htmlFor="menu-open">
            <FontAwesomeIcon icon="bars" />
            </label>

            {/* <div className='test'> */}
            <a className='menu-item blue'> <FontAwesomeIcon icon="user" /> </a>
            <a className='menu-item orange'> <FontAwesomeIcon icon="plus" /> </a>
            <a className='menu-item red'> <FontAwesomeIcon icon="heart" /> </a>
            {/* </div> */}
            </nav>
        </div>
    )
}
