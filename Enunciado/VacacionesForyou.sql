CREATE DATABASE IF NOT EXISTS `Vacacionesforyou` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `Vacacionesforyou`;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `Clientes`
--

CREATE TABLE `Clientes` (
  `id` int(11) NOT NULL,
  `nombre` varchar(50) NOT NULL,
  `apellidos` varchar(100) NOT NULL,
  `telefono` int(11) NOT NULL,
  `direccion` varchar(200) NOT NULL,
  `email` varchar(200) NOT NULL,
  `vip` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `Clientes`
--

INSERT INTO `Clientes` (`id`, `nombre`, `apellidos`, `telefono`, `direccion`, `email`, `vip`) VALUES
(0, 'Pedro', 'Sanz', 666777888, 'C/San Quintín, 19', 'pedrosanz@gmail.com', 1),
(1, 'Marta', 'Gimeno', 666111222, 'C/Federico García Lorca, 22 Duplicado', '222B@gmail.com', 1),
(2, 'Alberto', 'Rubio', 666444555, 'Plaza de España s/n', 'alber23@gmail.com', 0),
(3, 'Elena', 'Sanchez', 666999000, 'C/Maria de la O, 32', 'sanchez_da@hotmail.com', 0),
(4, 'Pablo', 'Martinez', 666333222, 'C/Paquita Salas, 112', 'pablo_martinez@gmail.com', 1),
(5, 'Felipa', 'Gracia', 564547901, 'C/Mercader, 2, 3ºA', 'garcia_felipa@hotmail.com', 0),
(6, 'Julia', 'Mendez', 639876765, 'C/Pedro Sanchez, 1', 'julia@hotmail.com', 0),
(7, 'Ana', 'Perez', 1111111, 'Plaza de las Flores, 32', 'anaperez@gmail.com', 0),
(8, 'Lorena', 'Ruiz', 640223119, 'C/San Francisco, 9', 'lore@hotmail.com', 1);