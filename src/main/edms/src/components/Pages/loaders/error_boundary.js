import React, { Component } from "react";


class ErrorBoundary extends Component {
    constructor(props) {
        super(props);
        this.state = {
            hasError: false,
            errorInfo: "",
            error: ""
        };
    }

    static getDerivedStateFromError(error) {
        console.log("eb")
        // Update state so the next render will show the fallback UI.
        return { hasError: true };
    }

    componentDidCatch(error, errorInfo) {
        console.log("eb")

        // You can also log the error to an error reporting service
        this.setState({
            error: error,
            errorInfo: errorInfo
        })
    }

    render() {
        if (this.state.hasError) {
            // You can render any custom fallback UI
            return <>
                <div>oops, our engineers(lux) are looking into it, diagnostics</div>
                {/* <div>{this.state.errorInfo}</div> */}
            </>;
        }

        return this.props.children;
    }
}


export default ErrorBoundary;