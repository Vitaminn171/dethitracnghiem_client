-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Dec 17, 2022 at 02:06 PM
-- Server version: 10.4.22-MariaDB
-- PHP Version: 8.1.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `dethitracnghiem`
--

-- --------------------------------------------------------

--
-- Table structure for table `exam`
--

CREATE TABLE `exam` (
  `ExamID` int(11) NOT NULL,
  `ExamTitle` varchar(255) NOT NULL,
  `Creator` int(11) NOT NULL,
  `SubjectID` int(11) NOT NULL,
  `LimitTime` int(11) DEFAULT NULL,
  `Visible` bit(1) NOT NULL DEFAULT b'1'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `exam`
--

INSERT INTO `exam` (`ExamID`, `ExamTitle`, `Creator`, `SubjectID`, `LimitTime`, `Visible`) VALUES
(2, 'Đề 2', 5, 1, 4, b'1'),
(3, 'Đề 3', 5, 2, 6, b'1'),
(4, 'Đề 4', 5, 3, 8, b'1'),
(24, 'Đề 24', 5, 4, 48, b'1'),
(25, 'Đề 25', 5, 5, 50, b'1'),
(26, 'Đề 26', 5, 1, 52, b'1'),
(27, 'Đề 27', 5, 2, 54, b'1'),
(28, 'Đề 28', 5, 3, 56, b'1');

-- --------------------------------------------------------

--
-- Table structure for table `examquestion`
--

CREATE TABLE `examquestion` (
  `ExamID` int(11) NOT NULL,
  `Number` int(11) NOT NULL,
  `Question` varchar(255) NOT NULL,
  `Choice1` varchar(255) NOT NULL,
  `Choice2` varchar(255) NOT NULL,
  `Choice3` varchar(255) NOT NULL,
  `Choice4` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `examquestion`
--

INSERT INTO `examquestion` (`ExamID`, `Number`, `Question`, `Choice1`, `Choice2`, `Choice3`, `Choice4`) VALUES
(2, 1, 'I’m afraid very few people know about the concert and almost no one will come. If only the posters _____________ on time.', 'had been hung', 'were hung', 'were hanged', 'were hanging'),
(2, 2, 'Jimmy sent his mother a ___________ of flowers for her birthday.', 'bunch', 'bar', 'pack', 'packet'),
(2, 3, 'My plans to travel around the world have _________ through because I couldn‟t save enough money.', 'fallen', 'dropped', 'given', 'put'),
(2, 4, '“I thought you bought a new fountain pen last week?” “Yes, I did but I left it at home. Can I borrow _____________ for a moment?”', 'one of yours', 'the one of you', 'one pen of you', 'the one of your pen'),
(2, 5, 'There are only a few minutes left, and the students is writing ________________.', 'under pressure', 'under the pressure', 'to pressure', 'with a pressure'),
(2, 6, 'A little farther down the street _______________.', 'is the inn I used to stay at', 'there is an inn where I used to stay in', 'the inn is the place where I used to stay', 'is there an inn in which I used to stay'),
(2, 7, 'Remember to appreciate what your friends do for you. You shouldn’t take them ________.', 'for granted', 'as usual', 'out of habit', 'as a rule'),
(2, 8, '“I wonder if you could help me?” - “______________”', 'I’ll do my best. What’s the problem?', 'Really? How nice.', 'Don’t mention it', 'No, what is it?'),
(2, 9, ' “ Don’t stay anywhere near the railway station at night. It’s dangerous.” - “_____________.”', 'I definitely won’t. Thank you.', 'They won’t like it, I bet.', 'Ok. That will do.', 'Where on Earth have you been?'),
(2, 10, '________every major judo title, Mark retired from international competition.', 'Having won', 'When he won', 'Winning', 'On winning'),
(3, 1, 'The opposition will be elected into government at the next election, without a ________ of a doubt.', 'shadow', 'shade', 'benefit', 'hue'),
(3, 2, 'She was ________ out of 115 applicants for the position of managing Director.', 'short-listed', 'short-changed', 'shorted-sighted', 'short-handed'),
(3, 3, 'It seems that the world record for this event is almost impossible to ________', 'beat', 'get', 'take', 'achieve'),
(3, 4, 'The smell was so bad that it completely ________ us off our food.', 'put', 'took', 'got', 'set'),
(3, 5, 'He has been waiting for this letter for days, and at ________ it has come.', 'last', 'the end', 'present', 'the moment'),
(3, 6, 'It is ________opportunity to see African wildlife in its natural environment.', 'the unique', 'a unique', 'an unique', 'unique'),
(3, 7, 'Peter and Thomas are talking about their mission. - Peter: “Is it important?” - Thomas: “________”', 'It’s a matter of life and death!', 'Not on your life!', 'No worry, that’s nothing', 'It’s ridiculous.'),
(3, 8, 'Ann is asking Mathew’s opinion after biology class. - Ann: “Does the global warming worry you?” - Mathew: “________”', 'I can’t bear to think about it.', 'Oh, it’s hotter and hotter.', 'What a shame!', 'I don’t like hot weather.'),
(3, 9, 'I’ve never really enjoyed going to the ballet or the opera; they’re not really my ________.', 'cup of tea', 'sweets and candy', 'biscuit', 'piece of cake'),
(3, 10, 'Paul is a very _______ character, he is never relaxed with strangers.', 'self-conscious', 'self-satisfied', 'self-directed', 'self-confident'),
(4, 1, 'The authorities _______ actions to stop illegal purchase of wild animals and their associated products effectively. However, they didn’t do so.', 'should have taken', 'had to take', 'needed have taken', 'must have taken'),
(4, 2, 'Although he is my friend, I find it hard to _______ his selfishness', 'put up with', 'catch up with', 'keep up with', 'come down with'),
(4, 3, 'We expected him at eight, but he finally _______at midnight.', 'turned up', 'came off', 'came to', 'turned in'),
(4, 4, 'The 1st week of classes at university is a little ______ because so many students get lost, change classes or go to the wrong place.', 'chaotic', 'arranged', 'uncontrolled', 'notorious'),
(4, 5, 'You can ask Matin anything about history. He actually has quite a good _____ for facts.', 'head', 'understanding', 'knowledge', 'ability'),
(4, 6, 'His jokes seemed to ______ a treat with his audience, if their laughter was any indication.', 'go off', 'go by', 'go along', 'go down'),
(4, 7, 'It was such a sad film that we all were reduced_______ tears at the end.', 'to', 'onto', 'with', 'into'),
(4, 8, 'The baby can`t even sit up yet, ______ stand and walk!', 'let alone', 'but for', 'all but', 'rather than'),
(4, 9, 'I don’t think that everyone likes the way he makes fun, ______?', 'do they', 'do I', 'don’t they', 'don`t I'),
(4, 10, 'Unfortunately, the injury may keep him out of football _______. He may never play again.', 'for good', 'now and then', 'once in a while', 'every so often'),
(24, 1, 'I’m afraid very few people know about the concert and almost no one will come. If only the posters _____________ on time.', 'had been hung', 'were hanging', 'were hung', 'were hanged'),
(24, 2, 'Jimmy sent his mother a ___________ of flowers for her birthday.', 'bunch', 'bar', 'pack', 'packet'),
(24, 3, 'My plans to travel around the world have _________ through because I couldn‟t save enough money.', 'fallen', 'dropped', 'given', 'put'),
(24, 4, '“I thought you bought a new fountain pen last week?” “Yes, I did but I left it at home. Can I borrow _____________ for a moment?”', 'one of yours', 'the one of you', 'one pen of you', 'the one of your pen'),
(24, 5, 'There are only a few minutes left, and the students is writing ________________.', 'under pressure', 'with a pressure', 'under the pressure', 'to pressure'),
(24, 6, 'The opposition will be elected into government at the next election, without a ________ of a doubt.', 'shadow', 'shade', 'benefit', 'hue'),
(24, 7, 'She was ________ out of 115 applicants for the position of managing Director.', 'short-listed', 'short-changed', 'shorted-sighted', 'short-handed'),
(25, 1, 'I’m afraid very few people know about the concert and almost no one will come. If only the posters _____________ on time.', 'had been hung', 'were hanging', 'were hung', 'were hanged'),
(25, 2, 'Jimmy sent his mother a ___________ of flowers for her birthday.', 'bunch', 'bar', 'pack', 'packet'),
(25, 3, 'My plans to travel around the world have _________ through because I couldn‟t save enough money.', 'fallen', 'dropped', 'given', 'put'),
(25, 4, '“I thought you bought a new fountain pen last week?” “Yes, I did but I left it at home. Can I borrow _____________ for a moment?”', 'one of yours', 'the one of you', 'one pen of you', 'the one of your pen'),
(25, 5, 'There are only a few minutes left, and the students is writing ________________.', 'under pressure', 'with a pressure', 'under the pressure', 'to pressure'),
(25, 6, 'The opposition will be elected into government at the next election, without a ________ of a doubt.', 'shadow', 'shade', 'benefit', 'hue'),
(25, 7, 'She was ________ out of 115 applicants for the position of managing Director.', 'short-listed', 'short-changed', 'shorted-sighted', 'short-handed'),
(26, 1, 'I’m afraid very few people know about the concert and almost no one will come. If only the posters _____________ on time.', 'had been hung', 'were hanging', 'were hung', 'were hanged'),
(26, 2, 'Jimmy sent his mother a ___________ of flowers for her birthday.', 'bunch', 'bar', 'pack', 'packet'),
(26, 3, 'My plans to travel around the world have _________ through because I couldn‟t save enough money.', 'fallen', 'dropped', 'given', 'put'),
(26, 4, '“I thought you bought a new fountain pen last week?” “Yes, I did but I left it at home. Can I borrow _____________ for a moment?”', 'one of yours', 'the one of you', 'one pen of you', 'the one of your pen'),
(26, 5, 'There are only a few minutes left, and the students is writing ________________.', 'under pressure', 'with a pressure', 'under the pressure', 'to pressure'),
(26, 6, 'The opposition will be elected into government at the next election, without a ________ of a doubt.', 'shadow', 'shade', 'benefit', 'hue'),
(26, 7, 'She was ________ out of 115 applicants for the position of managing Director.', 'short-listed', 'short-changed', 'shorted-sighted', 'short-handed'),
(26, 8, 'We expected him at eight, but he finally _______at midnight.', 'turned up', 'came off', 'came to', 'turned in'),
(26, 9, 'The authorities _______ actions to stop illegal purchase of wild animals and their associated products effectively. However, they didn’t do so.', 'should have taken', 'had to take', 'needed have taken', 'must have taken'),
(26, 10, 'I’m afraid very few people know about the concert and almost no one will come. If only the posters _____________ on time.', 'had been hung', 'were hanging', 'were hung', 'were hanged'),
(27, 1, 'I’m afraid very few people know about the concert and almost no one will come. If only the posters _____________ on time.', 'had been hung', 'were hanging', 'were hung', 'were hanged'),
(27, 2, 'Jimmy sent his mother a ___________ of flowers for her birthday.', 'bunch', 'bar', 'pack', 'packet'),
(27, 3, 'My plans to travel around the world have _________ through because I couldn‟t save enough money.', 'fallen', 'dropped', 'given', 'put'),
(27, 4, '“I thought you bought a new fountain pen last week?” “Yes, I did but I left it at home. Can I borrow _____________ for a moment?”', 'one of yours', 'the one of you', 'one pen of you', 'the one of your pen'),
(27, 5, 'There are only a few minutes left, and the students is writing ________________.', 'under pressure', 'with a pressure', 'under the pressure', 'to pressure'),
(27, 6, 'The opposition will be elected into government at the next election, without a ________ of a doubt.', 'shadow', 'shade', 'benefit', 'hue'),
(27, 7, 'She was ________ out of 115 applicants for the position of managing Director.', 'short-listed', 'short-changed', 'shorted-sighted', 'short-handed'),
(27, 8, 'We expected him at eight, but he finally _______at midnight.', 'turned up', 'came off', 'came to', 'turned in'),
(27, 9, 'The authorities _______ actions to stop illegal purchase of wild animals and their associated products effectively. However, they didn’t do so.', 'should have taken', 'had to take', 'needed have taken', 'must have taken'),
(27, 10, 'I’m afraid very few people know about the concert and almost no one will come. If only the posters _____________ on time.', 'had been hung', 'were hanging', 'were hung', 'were hanged'),
(28, 1, 'I’m afraid very few people know about the concert and almost no one will come. If only the posters _____________ on time.', 'had been hung', 'were hanging', 'were hung', 'were hanged'),
(28, 2, 'Jimmy sent his mother a ___________ of flowers for her birthday.', 'bunch', 'bar', 'pack', 'packet'),
(28, 3, 'My plans to travel around the world have _________ through because I couldn‟t save enough money.', 'fallen', 'dropped', 'given', 'put'),
(28, 4, '“I thought you bought a new fountain pen last week?” “Yes, I did but I left it at home. Can I borrow _____________ for a moment?”', 'one of yours', 'the one of you', 'one pen of you', 'the one of your pen'),
(28, 5, 'There are only a few minutes left, and the students is writing ________________.', 'under pressure', 'with a pressure', 'under the pressure', 'to pressure'),
(28, 6, 'The opposition will be elected into government at the next election, without a ________ of a doubt.', 'shadow', 'shade', 'benefit', 'hue'),
(28, 7, 'She was ________ out of 115 applicants for the position of managing Director.', 'short-listed', 'short-changed', 'shorted-sighted', 'short-handed'),
(28, 8, 'We expected him at eight, but he finally _______at midnight.', 'turned up', 'came off', 'came to', 'turned in'),
(28, 9, 'The authorities _______ actions to stop illegal purchase of wild animals and their associated products effectively. However, they didn’t do so.', 'should have taken', 'had to take', 'needed have taken', 'must have taken'),
(28, 10, 'I’m afraid very few people know about the concert and almost no one will come. If only the posters _____________ on time.', 'had been hung', 'were hanging', 'were hung', 'were hanged');

-- --------------------------------------------------------

--
-- Table structure for table `result`
--

CREATE TABLE `result` (
  `ResultID` int(11) NOT NULL,
  `ExamID` int(11) NOT NULL,
  `Examinee` varchar(255) NOT NULL,
  `Score` float NOT NULL,
  `Date` date NOT NULL,
  `CorrectQuiz` int(11) NOT NULL,
  `WrongQuiz` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `result`
--

INSERT INTO `result` (`ResultID`, `ExamID`, `Examinee`, `Score`, `Date`, `CorrectQuiz`, `WrongQuiz`) VALUES
(2, 3, 'ltm2022-2023@gmail.com', 2, '2022-12-15', 2, 8),
(3, 3, 'ltm2022-2023@gmail.com', 0, '2022-12-16', 0, 10),
(4, 3, 'ltm2022-2023@gmail.com', 2, '2022-12-16', 2, 8),
(5, 3, 'ltm2022-2023@gmail.com', 2, '2022-12-16', 2, 8),
(6, 4, 'ltm2022-2023@gmail.com', 1, '2022-12-16', 1, 9),
(7, 4, 'ltm2022-2023@gmail.com', 3, '2022-12-16', 3, 7),
(8, 4, 'ltm2022-2023@gmail.com', 2, '2022-12-17', 2, 8),
(20, 3, 'ltm2022-2023@gmail.com', 4, '2022-12-17', 4, 6);

-- --------------------------------------------------------

--
-- Table structure for table `subject`
--

CREATE TABLE `subject` (
  `SubjectID` int(11) NOT NULL,
  `SubjectName` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `subject`
--

INSERT INTO `subject` (`SubjectID`, `SubjectName`) VALUES
(1, 'English'),
(2, 'Math'),
(3, 'Physic'),
(4, 'Chemistry'),
(5, 'History');

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `UserID` int(11) NOT NULL,
  `Username` varchar(255) NOT NULL,
  `Password` varchar(255) NOT NULL,
  `Fullname` varchar(255) NOT NULL,
  `Birth` date NOT NULL,
  `Gender` bit(1) NOT NULL,
  `LogStatus` bit(1) NOT NULL DEFAULT b'0',
  `BlockLogin` bit(1) NOT NULL DEFAULT b'0',
  `BlockAddExam` bit(1) NOT NULL DEFAULT b'0',
  `BlockTakeExam` bit(1) NOT NULL DEFAULT b'0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`UserID`, `Username`, `Password`, `Fullname`, `Birth`, `Gender`, `LogStatus`, `BlockLogin`, `BlockAddExam`, `BlockTakeExam`) VALUES
(5, 'ltm2022-2023@gmail.com', 'e10adc3949ba59abbe56e057f20f883e', 'LTM2022-2023', '2022-12-03', b'1', b'1', b'0', b'0', b'0');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `exam`
--
ALTER TABLE `exam`
  ADD PRIMARY KEY (`ExamID`),
  ADD KEY `Creator` (`Creator`),
  ADD KEY `SubjectID` (`SubjectID`);

--
-- Indexes for table `examquestion`
--
ALTER TABLE `examquestion`
  ADD PRIMARY KEY (`ExamID`,`Number`),
  ADD KEY `ExamID` (`ExamID`);

--
-- Indexes for table `result`
--
ALTER TABLE `result`
  ADD PRIMARY KEY (`ResultID`),
  ADD KEY `ExamID` (`ExamID`),
  ADD KEY `Examinee` (`Examinee`);

--
-- Indexes for table `subject`
--
ALTER TABLE `subject`
  ADD PRIMARY KEY (`SubjectID`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`UserID`),
  ADD UNIQUE KEY `Username` (`Username`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `exam`
--
ALTER TABLE `exam`
  MODIFY `ExamID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=29;

--
-- AUTO_INCREMENT for table `result`
--
ALTER TABLE `result`
  MODIFY `ResultID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=22;

--
-- AUTO_INCREMENT for table `subject`
--
ALTER TABLE `subject`
  MODIFY `SubjectID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `UserID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `exam`
--
ALTER TABLE `exam`
  ADD CONSTRAINT `exam_ibfk_1` FOREIGN KEY (`SubjectID`) REFERENCES `subject` (`SubjectID`),
  ADD CONSTRAINT `exam_ibfk_2` FOREIGN KEY (`Creator`) REFERENCES `user` (`UserID`);

--
-- Constraints for table `examquestion`
--
ALTER TABLE `examquestion`
  ADD CONSTRAINT `examquestion_ibfk_1` FOREIGN KEY (`ExamID`) REFERENCES `exam` (`ExamID`);

--
-- Constraints for table `result`
--
ALTER TABLE `result`
  ADD CONSTRAINT `result_ibfk_2` FOREIGN KEY (`Examinee`) REFERENCES `user` (`Username`),
  ADD CONSTRAINT `result_ibfk_3` FOREIGN KEY (`ExamID`) REFERENCES `exam` (`ExamID`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
