import React from "react";
import "../styles.css";

function ArrayDisplay({ array }) {
    if (!array || array.length === 0) return null;

    return (
        <div id="arrayDisplay" className="mb-4">
            {array.map((num, index) => (
                <span key={index} className="array-box">
          [{num}]
        </span>
            ))}
        </div>
    );
}

export default ArrayDisplay;
