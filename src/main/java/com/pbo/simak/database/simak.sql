-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Generation Time: Jan 14, 2022 at 12:26 AM
-- Server version: 5.7.33
-- PHP Version: 8.0.11

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `simak`
--

-- --------------------------------------------------------

--
-- Table structure for table `expenditure`
--

CREATE TABLE `expenditure` (
  `expenditure_id` int(11) NOT NULL,
  `expenditure_name` varchar(255) NOT NULL,
  `expenditure_price` varchar(11) NOT NULL,
  `transaction_time` varchar(255) NOT NULL,
  `description` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `expenditure`
--

INSERT INTO `expenditure` (`expenditure_id`, `expenditure_name`, `expenditure_price`, `transaction_time`, `description`) VALUES
(1, 'Operasional', '700000', '2022-01-13', 'Memperbaiki Kamar Mandi'),
(2, 'Pendanaan', '300000', '2022-01-10', 'Sponsor acara lomba coding');

-- --------------------------------------------------------

--
-- Table structure for table `product`
--

CREATE TABLE `product` (
  `product_id` int(11) NOT NULL,
  `product_name` varchar(255) NOT NULL,
  `product_price` varchar(255) NOT NULL,
  `product_category` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `product`
--

INSERT INTO `product` (`product_id`, `product_name`, `product_price`, `product_category`) VALUES
(2, 'Martabak', '15000', 'Makanan'),
(3, 'Legion 15ARH', '18500000', 'Laptop');

-- --------------------------------------------------------

--
-- Table structure for table `transaction_product`
--

CREATE TABLE `transaction_product` (
  `transaction_id` int(11) NOT NULL,
  `product_name` varchar(255) NOT NULL,
  `product_count` int(255) NOT NULL,
  `total_price` varchar(255) NOT NULL,
  `description` text NOT NULL,
  `transaction_time` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `transaction_product`
--

INSERT INTO `transaction_product` (`transaction_id`, `product_name`, `product_count`, `total_price`, `description`, `transaction_time`) VALUES
(1, 'Legion 15ARH', 2, '37000000', 'Penjualan awal tahun baru', '2022-01-12');

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `id` int(11) NOT NULL,
  `full_name` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`id`, `full_name`, `email`, `password`) VALUES
(1, 'Erlangga Dewa', 'erlanggadewa7@gmail.com', '1000:68c8c8d482de64d42ffad7074afbaca4:6a570b8eab428051db856826e6142954e803b66cebb3e2ffdf9a7ffc0c83d66f95e187c261c5feb29b6a1845f4d65bc34c18c9128833d21f9496f00ea3123c36'),
(2, 'e', 'admin@gmail.com', '1000:2575c39c1d1c1929818906d4bfdfb9c5:52481af6db2d65a9bdb0e764d98985d3aaeb3264d69584314272c9dc9f658fd69fe6bb8b5ba78cb7314671a952e93db9343c7ca3c87a0c8eb3cfb2cba4d362ae'),
(3, 'Erlangga', 'erlangga@example.com', '1000:7e5136dca0fdc5a8c7740fb3b723ea57:48a461bb8e660bfda66400673a1afd9eadd6b56bec70e594fe9360fc17cc2de3a364e0c6f5ca945043ec76be9610b9c192ab9a0dca4601da13ec4fe40feaf8dc'),
(4, 'Todi Mahendra', 'erlangga@gmail.com', '1000:3f0f42c870aba7e6d9185340ea3ada6a:9939405dc17905388c72dd2525f8aab37bb3f5856deb1aab3c587c9948d3d17b6f7ee3e0bd845ec97716a8a84b6bb568a41be9f610539f8eae23a3585e41fcf9');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `expenditure`
--
ALTER TABLE `expenditure`
  ADD PRIMARY KEY (`expenditure_id`);

--
-- Indexes for table `product`
--
ALTER TABLE `product`
  ADD PRIMARY KEY (`product_id`);

--
-- Indexes for table `transaction_product`
--
ALTER TABLE `transaction_product`
  ADD PRIMARY KEY (`transaction_id`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `expenditure`
--
ALTER TABLE `expenditure`
  MODIFY `expenditure_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `product`
--
ALTER TABLE `product`
  MODIFY `product_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `transaction_product`
--
ALTER TABLE `transaction_product`
  MODIFY `transaction_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
