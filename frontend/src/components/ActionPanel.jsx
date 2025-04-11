import React, { useState } from "react";

function ActionPanel({ gameId, setArray, setMessage, setGameInfo }) {
    const [index1, setIndex1] = useState("");
    const [index2, setIndex2] = useState("");
    const [pass, setPass] = useState("");

    const performAction = async () => {
        if (gameId === null) {
            alert("Start a game first!");
            return;
        }

        const actionData = {
            gameID: gameId,
            pass: parseInt(pass),
            index1: parseInt(index1),
            index2: parseInt(index2),
        };

        try {
            const res = await fetch("/game/action", {
                method: "POST",
                headers: { "Content-Type": "application/json" },
                body: JSON.stringify(actionData),
            });

            const data = await res.json();
            setArray(data.array);           // Update array after swap/insert
            setMessage(data.message);       // Show result of the move
            setGameInfo(data);              // Store updated game state
        } catch (err) {
            console.error("Error performing action:", err);
        }
    };

    return (
        <div className="mt-6">
            <h3 className="font-bold mb-2">Swap / Insert</h3>

            <input
                type="number"
                value={index1}
                onChange={(e) => setIndex1(e.target.value)}
                placeholder="Index 1"
                className="m-1 p-2 w-24 text-center"
            />
            <input
                type="number"
                value={index2}
                onChange={(e) => setIndex2(e.target.value)}
                placeholder="Index 2"
                className="m-1 p-2 w-24 text-center"
            />
            <input
                type="number"
                value={pass}
                onChange={(e) => setPass(e.target.value)}
                placeholder="Pass"
                className="m-1 p-2 w-24 text-center"
            />

            <button
                onClick={performAction}
                className="bg-green-600 text-white px-4 py-2 rounded mt-2"
            >
                Perform Action
            </button>
        </div>
    );
}

export default ActionPanel;
