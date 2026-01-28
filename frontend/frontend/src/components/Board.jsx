import Column from "./Column";

export default function Board({ tasks, onTaskClick }) {
  return (
    <div className="board">
      <Column
        title="To Do"
        status="TODO"
        tasks={tasks}
        onTaskClick={onTaskClick}
      />

      <Column
        title="In Progress"
        status="IN_PROGRESS"
        tasks={tasks}
        onTaskClick={onTaskClick}
      />

      <Column
        title="Done"
        status="DONE"
        tasks={tasks}
        onTaskClick={onTaskClick}
      />
    </div>
  );
}
