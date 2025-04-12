import React from "react";
import "../styles.css";

function ArrayDisplay({ array }) {
    if (!array || array.length === 0) return null;

    return (
        <div className="flex flex-wrap justify-center gap-4 mb-6">
            {array.map((num, index) => (
                <div
                    key={index}
                    className="w-12 h-12 flex items-center justify-center bg-blue-100 border border-blue-400 rounded-lg text-lg font-semibold shadow"
                >
                    {num}
                </div>
            ))}
        </div>
    );
}

export default ArrayDisplay;
