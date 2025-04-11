import React, { useState } from "react";

function GameSetup({ setGameId, setArray, setGameInfo }) {
    const [algorithm, setAlgorithm] = useState("");
    const [arraySize, setArraySize] = useState(5);

    const startGame = async () => {
        if (!algorithm || arraySize < 2) {
            alert("Please select an algorithm and valid array size.");
            return;
        }

        try {
            const res = await fetch("/game/start", {
                method: "POST",
                headers: { "Content-Type": "application/json" },
                body: JSON.stringify({ algorithm, arraySize }),
            });

            const data = await res.json();
            setGameId(data.gameId);
            setArray(data.array);
            setGameInfo(data);
        } catch (err) {
            console.error("Failed to start game:", err);
        }
    };

    return (
        <div className="mb-4">
            <label className="mr-2">Algorithm:</label>
            <select
                value={algorithm}
                onChange={(e) => setAlgorithm(e.target.value)}
                className="p-2 m-2"
            >
                <option value="" disabled>Select Algorithm</option>
                <option value="bubble_sort">Bubble Sort</option>
                <option value="selection_sort">Selection Sort</option>
                <option value="insertion_sort">Insertion Sort</option>
                <option value="merge_sort">Merge Sort</option>
            </select>

            <label className="mr-2">Array Size:</label>
            <input
                type="number"
                value={arraySize}
                min={2}
                max={100}
                onChange={(e) => setArraySize(Number(e.target.value))}
                className="p-2 m-2 w-20 text-center"
            />

            <button
                onClick={startGame}
                className="bg-blue-500 text-white px-4 py-2 rounded"
            >
                Start Game
            </button>
        </div>
    );
}

export default GameSetup;
