import TaskCard from "./TaskCard";

export default function Column({ title, status, tasks, onTaskClick }) {
  const columnTasks = tasks.filter((task) => task.status === status);

  return (
    <div className="column">
      <h2 className="column-title">{title}</h2>

      {columnTasks.length === 0 && (
        <p className="empty-text">No tasks</p>
      )}

      {columnTasks.map((task) => (
        <TaskCard
          key={task.id}
          task={task}
          onClick={() => onTaskClick(task)}
        />
      ))}
    </div>
  );
}
