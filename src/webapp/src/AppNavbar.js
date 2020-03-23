import React, {Component} from 'react';
import {Navbar, NavbarBrand} from 'reactstrap';
import {Link} from 'react-router-dom';

export default class AppNavbar extends Component {
    constructor(props) {
        super(props);
        this.state = {isOpen: false};
        this.toggle = this.toggle.bind(this);
    }

    toggle() {
        this.setState({
            isOpen: !this.state.isOpen
        });
    }

    render() {
        return <Navbar color="dark" dark expand="md">
            <div className="float-right">
                <NavbarBrand tag={Link} to="/persons">Home</NavbarBrand>
            </div>
            <div className="float-right">
                <NavbarBrand tag={Link} to="/person/new">Add Person</NavbarBrand>
            </div>
            <div className="float-right">
                <NavbarBrand tag={Link} to="/relation/new">Add Relation</NavbarBrand>
            </div>
            <div className="float-right">
                <NavbarBrand tag={Link} to="/familytree/new">Generate Family Tree</NavbarBrand>
            </div>
        </Navbar>;
    }
}