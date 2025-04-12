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
        <div className="flex flex-col md:flex-row items-center justify-center gap-4 mb-6">

            <div className="flex items-center gap-2">
                <label className="font-medium">Algorithm:</label>
                <select
                    value={algorithm}
                    onChange={(e) => setAlgorithm(e.target.value)}
                    className="p-2 border rounded-lg shadow-sm"
                >
                    <option value="" disabled>Select Algorithm</option>
                    <option value="bubble_sort">Bubble Sort</option>
                    <option value="selection_sort">Selection Sort</option>
                    <option value="insertion_sort">Insertion Sort</option>
                    <option value="merge_sort">Merge Sort</option>
                </select>
            </div>

            <div className="flex items-center gap-2">
                <label className="font-medium">Array Size:</label>
                <input
                    type="number"
                    value={arraySize}
                    min={2}
                    max={100}
                    onChange={(e) => setArraySize(Number(e.target.value))}
                    className="p-2 border rounded-lg w-24 text-center shadow-sm"
                />
            </div>

            <button
                onClick={startGame}
                className="px-4 py-2 bg-indigo-600 text-white rounded-lg shadow hover:bg-indigo-700 transition"
            >
                Start Game
            </button>
        </div>

    );
}

export default GameSetup;
