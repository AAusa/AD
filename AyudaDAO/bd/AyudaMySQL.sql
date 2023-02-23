-- phpMyAdmin SQL Dump
-- version 4.5.4.1deb2ubuntu1
-- http://www.phpmyadmin.net
--
-- Servidor: localhost
-- Tiempo de generación: 23-02-2023 a las 12:13:36
-- Versión del servidor: 5.7.11-0ubuntu6
-- Versión de PHP: 7.0.4-7ubuntu2

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `Ayuda`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `Necesidad`
--

CREATE TABLE `Necesidad` (
  `IdVoluntario` int(10) NOT NULL,
  `IdNecesitado` int(10) NOT NULL,
  `Nombre` varchar(30) NOT NULL,
  `Disponibilidad` varchar(30) NOT NULL,
  `id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `Necesidad`
--

INSERT INTO `Necesidad` (`IdVoluntario`, `IdNecesitado`, `Nombre`, `Disponibilidad`, `id`) VALUES
(1, 1, 'Transporte', 'Tarde', 1),
(2, 2, 'Comida', 'Tarde', 2),
(3, 3, 'Limpieza', 'Noche', 3);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `Necesitado`
--

CREATE TABLE `Necesitado` (
  `Id` int(10) NOT NULL,
  `Nombre` varchar(30) NOT NULL,
  `Apellido` varchar(30) NOT NULL,
  `Edad` int(3) NOT NULL,
  `Sexo` varchar(15) NOT NULL,
  `EstadoCivil` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `Necesitado`
--

INSERT INTO `Necesitado` (`Id`, `Nombre`, `Apellido`, `Edad`, `Sexo`, `EstadoCivil`) VALUES
(1, 'Pepe', 'Perez', 2, 'Masculino', 'Soltero'),
(2, 'Francisca', 'Legarre', 76, 'Femenino', 'Casada'),
(3, 'Eustaquia', 'Ferrer', 54, 'Femenino', 'Casada');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `Voluntario`
--

CREATE TABLE `Voluntario` (
  `Id` int(10) NOT NULL,
  `Nombre` varchar(30) NOT NULL,
  `Apellido` varchar(30) NOT NULL,
  `Edad` int(3) NOT NULL,
  `Sexo` varchar(15) NOT NULL,
  `EstadoCivil` varchar(30) NOT NULL,
  `Disponibilidad` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `Voluntario`
--

INSERT INTO `Voluntario` (`Id`, `Nombre`, `Apellido`, `Edad`, `Sexo`, `EstadoCivil`, `Disponibilidad`) VALUES
(1, 'Juan', 'Garcia', 54, 'Masculino', 'Casado', 'Mañana'),
(2, 'Pepa', 'Garrote', 64, 'Femenino', 'Casada', 'Tarde'),
(3, 'Jimena', 'Valero', 43, 'Femenino', 'Casada', 'Noche');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `Necesidad`
--
ALTER TABLE `Necesidad`
  ADD PRIMARY KEY (`id`),
  ADD KEY `IdVoluntario` (`IdVoluntario`),
  ADD KEY `FKIdNecesitado` (`IdNecesitado`);

--
-- Indices de la tabla `Necesitado`
--
ALTER TABLE `Necesitado`
  ADD PRIMARY KEY (`Id`);

--
-- Indices de la tabla `Voluntario`
--
ALTER TABLE `Voluntario`
  ADD PRIMARY KEY (`Id`);

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `Necesidad`
--
ALTER TABLE `Necesidad`
  ADD CONSTRAINT `FKIdNecesitado` FOREIGN KEY (`IdNecesitado`) REFERENCES `Necesitado` (`Id`),
  ADD CONSTRAINT `FKIdVoluntario` FOREIGN KEY (`IdVoluntario`) REFERENCES `Voluntario` (`Id`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
